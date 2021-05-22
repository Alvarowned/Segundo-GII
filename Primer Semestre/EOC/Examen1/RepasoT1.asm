.model small
.stack 100h
.data
    Numero DW 83C4h
.code
Inicio:
    mov ax, @data
    mov ds, ax
    
    push Numero
    pop ax
    
    neg ax
    
    mov cx, 2000h
    imul cx          ;Mul con signo, guarda en ax concatenado con
                     ;dx (en ax si solo es un byte)
    
    mov ah, 4Ch
    int 21h
end Inicio