.model small
.stack 100h
.data
    Op1 DB 20h
    Op2 DB 10h
    ResSuma DW 0000h
    ResResta DW 0000h
    ResMult DW 0000h
    ResDivi DW 0000h
.code
Inicio:
    mov ax, @data
    mov ds, ax 
    
    sub ax, ax
    
    mov al, Op1
    add al, Op2
    mov ResSuma, ax  
    
    sub ax, ax
    
    mov al, Op1
    sub al, Op2
    mov ResResta, ax
    
    mov al, Op1
    mov bl, Op2
    mul bl    
    mov ResMult, ax
    
    sub ax, ax
    
    mov al, Op1
    mov bl, Op2
    div bl
    mov ResDivi, ax
    
    mov ah, 4Ch
    int 21h
end Inicio