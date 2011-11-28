section .text

; ------------------------------
global write
global exit

; ------------------------------
%define system_call		0x80
%define system_write	0x4
%define system_exit  	0x1

; ------------------------------
section .text

write:
    mov eax, system_write              ; system call write code
    int system_call                  ; make system call
    ret

exit:
    mov eax, system_exit              ; system call exit code
    int system_call                  ; make system call
    ret

; ------------------------------
section .data

digit_format db '%10d', 0
