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
%define system_call             0x80
%define system_write    0x4
%define system_exit     0x1
%define start_value     1
%define end_value               10
%define return_ok               0
%define foo_value               3
%define bar_value               5
%define qix_value               7
%define buffer_size     32

; ------------------------------
; see http://stackoverflow.com/questions/5649/x86-assembly-on-a-mac
; aligns esp to 16 bytes in preparation for calling a C library function
; arg is number of bytes to pad for function arguments, this should be a multiple of 16
; unless you are using push/pop to load args
%macro clib_prolog 1
    mov ebx, esp        ; remember current esp
    and esp, 0xFFFFFFF0 ; align to next 16 byte boundary (could be zero offset!)
    sub esp, 12         ; skip ahead 12 so we can store original esp
    push ebx            ; store esp (16 bytes aligned again)
    add esp, %1         ; pad for arguments (make conditional?)
%endmacro

; arg must match most recent call to clib_prolog
%macro clib_epilog 1
    sub esp, %1         ; remove arg padding
    pop ebx             ; get original esp
    mov esp, ebx        ; restore
%endmacro

%macro printf 1
        push dword %1
        push dword string_format
        call _printf
%endmacro

%macro is_divisible 1
        xor     edx, edx
        mov     eax, ebx
        mov     esi, %1
        idiv    esi
%endmacro

global _main

; ------------------------------
_main:
        push    ebp
    mov         ebp, esp
    sub         esp, 16
    push        ebx
    push        esi
    push        edi     

; buffer = malloc(buffer_size) 
        call    _malloc
        test    eax, eax
        jz              near malloc_failed
        mov     [buffer], eax

; number = malloc(buffer_size) 
        sub     esp, 3*4
        push    dword buffer_size
        call    _malloc
        add     esp, 3*4
        test    eax, eax
        jz              near malloc_failed
        mov     [number], eax

; for(ebx = start_value; ebx < end_value; ebx ++) 
        mov     ebx, start_value
main_loop:
        ; bzero(buffer, buffer_size)
        sub     esp, 3*4
        push    dword buffer_size
        push    dword buffer
        call    _bzero
        add     esp, 3*4
        
        ; bzero(number, buffer_size)
        sub     esp, 3*4
        push    dword buffer_size
        push    dword number
        call    _bzero
        add     esp, 4*4

        ; sprintf(buffer, "%d", ebx) 
        sub     esp, 7*4
        push    dword ebx
        push    dword digit_format
        push    dword buffer
        call    _sprintf
        add     esp, 7*4
        
;       printf buffer
        

;       printf  number
        
check_bar:    
        
continue:
                
        cmp             ebx, end_value
        jz              end
        inc             ebx
        jmp             main_loop 

end:
        push    dword return_ok
        call    exit

malloc_failed:
        printf malloc_failed_msg
        call    exit

exit:
    mov         eax, system_exit
    int         system_call
    ret


; ------------------------------
section .data

; printf        
        new_line                db  10, 0
        string_format   db '%s', 10, 0
        digit_print             db '%d',10, 0
        message                 db 'FooBarQix',10, 0
; scanf
        digit_format            db '%d', 10, 0
        two_digits_format       db '%d%d', 10, 0
; malloc
        malloc_failed_msg       dw 'malloc failed', 10, 0
        buffer                          dw 0  
        number                          dw 0  
        
