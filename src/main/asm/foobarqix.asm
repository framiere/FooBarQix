; to compile :
; 
; nasm -g -f macho foobarqix.asm
; gcc -arch i386 -o foobarqix  methods.o foobarqix.o
; 
; to debug :
;
; gdb foobarqix
; set disassembly-flavor intel
; break main
; disassemble main
; nexti
; run
; info stack

section .text

; ------------------------------
; OS X uses _ for symbol prefix
extern _printf
extern _sprintf
extern _malloc
extern _bzero

; ------------------------------
%define system_call		0x80
%define system_write	0x4
%define system_exit  	0x1
%define start_value 	1
%define end_value 		10
%define return_ok 		0
%define foo_value 		3
%define bar_value 		5
%define qix_value 		7
%define buffer_size 	32

%macro printf 1
	sub 	esp, 3*4
	push dword %1
	push dword string_format
	call _printf
	add 	esp, 5*4
%endmacro

%macro is_divisible 1
	xor     edx, edx
	mov 	eax, ebx
	mov 	esi, %1
	idiv  	esi
%endmacro

global _main

; ------------------------------
_main:
 	push	ebp
    mov		ebp, esp
    sub 	esp, 32
    push 	ebx
    push	esi
    push	edi	

; buffer = malloc(buffer_size) 
	sub 	esp, 2*4 
 	push	dword buffer_size
	call	_malloc
	add 	esp, 2*4
	test	eax, eax
	jz		near malloc_failed
	mov 	[buffer], eax

; number = malloc(buffer_size) 
	sub 	esp, 3*4
 	push	dword buffer_size
	call	_malloc
	add 	esp, 3*4
	test	eax, eax
	jz		near malloc_failed
	mov 	[number], eax

; for(ebx = start_value; ebx < end_value; ebx ++) 
	mov 	ebx, start_value
main_loop:
	

	; sprintf(number, "%d", ebx);
	sub 	esp, 3*4
 	push	dword 12
 	push	dword digit_format
 	push	dword number
	call	_sprintf
	add 	esp, 3*4

	printf number
	
continue:
	cmp		ebx, end_value
	jz		end
	inc		ebx
	jmp		main_loop 

end:
	push    dword return_ok
	call    exit

malloc_failed:
	printf 	malloc_failed_msg
	call 	exit

exit:
    mov 	eax, system_exit
    int 	system_call
    ret


; ------------------------------
section .data

; printf	
	new_line		db  10, 0
	string_format	db '%s', 10, 0
	digit_print		db '%d',10, 0
	message			db 'FooBarQix',10, 0
; sprintf
	digit_format		db '%d', 0
	two_digits_format	db '%d%d', 0
; malloc
	malloc_failed_msg	dw 'malloc failed', 10, 0
	buffer				dw 0  
	number				dw 0  
	