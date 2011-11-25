;
; nasm -g -f macho foobarqix.asm
; nasm -g -f macho methods.asm
; gcc -arch i386 -o foobarqix  methods.o foobarqix.o
;

section .text

; ------------------------------
; OS X uses _ for symbol prefix
extern _printf

; ------------------------------
; from methods.asm
extern print_digit
extern write
extern exit

; ------------------------------
%define stdout  1
%define max  10
%define printf _printf

global _main				; make the main function externally visible

; ------------------------------
_main:
	; please refer to http://fabiensanglard.net/macosxassembly/index.php 
	; to understand what this is all about
    push    ebp
    mov     ebp, esp


	mov ebx, 0
loop:
	cmp ebx, max
	jz done

	; call printf
	push  ebx      
	push dword digit_format        
  	call printf
  	
  	inc ebx
  	jmp loop

done:
	; write
	push dword foobarqix_length						   
	push dword foobarqix_string
	push dword stdout
	call write
	
	; exit 
	push dword 4
	mov eax, 4			  
	call exit 

; ------------------------------
section .data

  foobarqix_string db "foo bar qix", 10, 0
  foobarqix_length equ $-foobarqix_string
  digit_format db '%d', 10, 0	