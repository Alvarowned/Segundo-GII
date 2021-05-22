.model small
.stack 100h
.data
    Operando1 DB 10h
    Operando2 DB 20h
    Resultado DW 0000h
.code
Inicio:
    mov ax, @data
    mov ds, ax
    
    sub ax, ax
    
    mov al, Operando1
    mov al, Operando2
    mov Resultado, ax
    
    mov ah, 4Ch
    int 21h
end Inicio