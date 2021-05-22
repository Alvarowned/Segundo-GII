.model small
.stack 100h
.data

.code
Inicio:
    mov ax, @data
    mov ds, ax
    
    mov dx, 01h
    cmp dx, 01h
    
    jz Prueba          ;jz es igual que je
    mov bl, 01h
    
    Prueba:  
    mov cx, 4
    Bucle:
        inc bx
        loop Bucle
    mov ah, 4Ch
    int 21h
end Inicio