;
; nasm -f macho foobarqix.asm
; ld -o foobarqix -e mystart foobarqix.o
;

section .text

global mystart                ; make the main function externally visible

mystart:

; 1 print message

    ; 1a prepare the arguments for the system call to write
    push dword foobarqix_length                           
    push dword foobarqix_string
    push dword 1              ; file descriptor value

    ; 1b make the system call to write
    mov eax, 0x4              ; system call number for write
    sub esp, 4                ; OS X "extra space" on stack
    int 0x80                  ; make the actual system call

    ; 1c clean up the stack
    add esp, 16               ; 3 args * 4 bytes/arg + 4 bytes extra space = 16 bytes
    
; 2 exit the program

    push dword 0              ; exit status 
    mov eax, 0x1              ; system call number for exit
    sub esp, 4                ; OS X "extra space" on stack
    int 0x80                  ; make the system call

; 2c no need to clean up the stack because no code here would executed: already exited
    
section .data

  foobarqix_string db "foo bar qix", 10
  foobarqix_length equ $-foobarqix_string