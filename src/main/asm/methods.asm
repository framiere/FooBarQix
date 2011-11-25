section .text

global write
global exit

section .text

write:
    mov eax, 0x4              ; system call write code
    int 0x80                  ; make system call
    ret

exit:
    mov eax, 0x1              ; system call exit code
    int 0x80                  ; make system call
    ; no need to return
