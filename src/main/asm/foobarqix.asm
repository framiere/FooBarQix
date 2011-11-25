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

global _main				; make the main function externally visible

; ------------------------------
_main:
    push    ebp
    mov     ebp, esp

	push dword 10      
	push dword digit_format        
  	call    _printf

	; write
	push dword foobarqix_length						   
	push dword foobarqix_string
	push dword stdout
	call write
	add esp, 12			   	; 3 args * 4 bytes/arg 
	
	; exit 
	push dword 4
	mov eax, 4			  
	call exit 


; ------------------------------
section .data

  foobarqix_string db "foo bar qix", 10, 0
  foobarqix_length equ $-foobarqix_string
  digit_format db '%10d', 10, 0	