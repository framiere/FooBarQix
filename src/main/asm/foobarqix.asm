;
; nasm -f macho foobarqix.asm
; ld -o foobarqix -e mystart foobarqix.o
;

section .text

global mystart                ; make the main function externally visible

write:
    mov eax, 0x4              ; system call write code
    int 0x80                  ; make system call
    ret

exit:
    mov eax, 0x1              ; system call exit code
    int 0x80                  ; make system call
    ; no need to return

mystart:

    ; 1a prepare the arguments for the system call to write
    push dword foobarqix_length                           
    push dword foobarqix_string
    push dword 1              ; file descriptor value
	call write

    ; 1c clean up the stack
    add esp, 12
    
; 2 exit the program

    push dword 0              ; exit status
    call exit 

section .data

  foobarqix_string db "foo bar qix", 10
  foobarqix_length equ $-foobarqix_string