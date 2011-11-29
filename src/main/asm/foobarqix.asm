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
extern _strcpy

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

%macro printf 1
	push dword %1
	push dword string_format
	call _printf
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
	; enter
 	push ebp
    mov ebp, esp
    push ebx
    push esi
    push edi	

; buffer = malloc(42) 
	sub 	esp, 1*4 + 4
 	push	dword 42
	call	_malloc
	add 	esp, 1*4 + 4
	test	eax, eax
	jz		near malloc_failed
	mov 	[buffer], eax
	printf 	malloc_success_msg

; manual equivalent of strcpy(buffer, "Hell!\n")
	call manual_copy
	
; printf(buffer)
	printf 	[buffer]

; strcpy(buffer, message)
	push 	message
	push 	dword [buffer]
	call 	_strcpy
	
; printf(buffer)
	printf 	[buffer]
	
; sprintf(buffer, "%d\n",42) 
	sub 	esp, 3*4
 	push	dword 42
 	push	dword digit_format
 	push	dword buffer
 	call 	_sprintf
	add 	esp, 3*4

; printf(buffer)
	printf buffer

; sprintf(buffer, "%d%d\n",42, 99) 
	sub 	esp, 4*4
 	push	dword 99
 	push	dword 42
 	push	dword two_digits_format
 	push	dword buffer
 	call 	_sprintf
	add 	esp, 4*4

; printf(buffer)
	printf buffer

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

manual_copy:
	mov eax, [buffer]
	mov byte [eax + 0], 'H' 
	mov byte [eax + 1], 'e' 
	mov byte [eax + 2], 'l' 
	mov byte [eax + 3], 'l' 
	mov byte [eax + 4], '!' 
	mov byte [eax + 5], 10
	mov byte [eax + 6], 0
	ret
	
; ------------------------------
section .data

; printf	
	new_line		db  10, 0
	string_format	db '%s', 0
	digit_print		db '%d',10, 0
	message			db 'FooBarQix',10, 0
; scanf
	digit_format		db '%d', 10, 0
	two_digits_format	db '%d%d', 10, 0
; malloc
	malloc_success_msg	dw 'malloc success', 10, 0
	malloc_failed_msg	dw 'malloc failed', 10, 0
	buffer				dw 0  
