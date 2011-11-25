;
; nasm -f macho foobarqix.asm
; nasm -f macho methods.asm
; ld -o foobarqix -e start methods.o foobarqix.o
;

section .text

extern write
extern exit

global start                ; make the main function externally visible

start:
	; write
    push dword foobarqix_length                           
    push dword foobarqix_string
    push dword 1              ; sysout
	call write
    add esp, 12				  ; 3 args * 4 bytes/arg 
    
    ; exit 
    push dword 0              
    call exit 

section .data
  foobarqix_string db "foo bar qix", 10, 0
  foobarqix_length equ $-foobarqix_string