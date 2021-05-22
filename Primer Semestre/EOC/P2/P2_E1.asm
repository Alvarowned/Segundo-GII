.model small
.stack 100h
.data
	Terminar EQU 4Ch
.code
Inicio:
 	mov ax, @data
 	mov ds, ax
 	mov al, 10h
 	mov cl, 4
 	shr al, cl
 	mov ah, 4Ch
 	int 21h
end Inicio