; nasm -g -f macho foobarqix.asm
; nasm -g -f macho methods.asm
; gcc -arch i386 -o foobarqix  methods.o foobarqix.o
;

section .text

; ------------------------------
; OS X uses _ for symbol prefix
extern _printf
extern _itoa
extern _malloc

; ------------------------------
; from methods.asm
extern print_digit
extern write
extern exit

; ------------------------------
%define stdout 1
%define start_value 1
%define end_value 10
%define return_ok 0
%define foo_value 3
%define bar_value 5
%define qix_value 7

%macro printf_digit 1
	push %1
	push dword printf_digit_format        
  	call _printf
%endmacro

%macro printf 1
	push dword %1
	push dword printf_string_format
	call _printf
%endmacro

%macro is_divisible 1
	xor     edx, edx
	mov 	eax, ebx
	mov 	esi, %1
    idiv  	esi
%endmacro

%macro reset_foo_bar_qix 0
	mov  dword  [foo_divisible], 0
	mov  dword  [bar_divisible], 0
	mov  dword  [qix_divisible], 0
%endmacro

global _main

; ------------------------------
_main:
	; please refer to http://fabiensanglard.net/macosxassembly/index.php 
	; to understand what this is all about
    push    ebp
    mov     ebp, esp

	mov		ebx, start_value

	reset_foo_bar_qix
	
main_loop:
	reset_foo_bar_qix
	printf_digit ebx

is_foo_divisible:
	is_divisible foo_value
    cmp 	edx, 0
    jnz 	is_bar_divisible
    mov 	dword [foo_divisible], 1
	printf 	foo

is_bar_divisible:    
	is_divisible bar_value
    cmp 	edx, 0
    jnz 	is_qix_divisible
    mov 	dword [bar_divisible], 1
	printf 	bar
	
is_qix_divisible:    
	is_divisible qix_value
    cmp 	edx, 0
    jnz 	main_loop_inc
	printf 	qix
    mov 	dword [qix_divisible], 1
	
main_loop_inc:
	printf 	new_line

  	inc 	ebx
  	cmp 	ebx, end_value
  	jnz  	main_loop

exit_program:

	push 	dword return_ok
	call 	exit
	
; ------------------------------
section .data

; printf	
	foo						db 'foo', 0
	bar						db 'bar', 0
	qix						db 'qix', 0
	new_line 				db  10, 0
	printf_string_format 	db '%s', 0
	printf_digit_format 	db '%d', 0
	buffer			        times 64 db 0  
; state
	foo_divisible			dw 0 
	bar_divisible			dw 0 
	qix_divisible			dw 0 
	