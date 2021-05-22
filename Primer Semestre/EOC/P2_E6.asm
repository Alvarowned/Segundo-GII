.model small
.stack 100h
.data 
	Dir1 DB 10h, 20h, 30h, 40h
 	Dir2 DB 50h, 60h, 70h, 80h
 	Resul DB ?, ?, ?, ? 
 	ResulS DW 'Resutado1: ', 'Resultado2: ', 'Resultado3: ', 'Resultado4: '
.code
Inicio:
 	mov ax, @data
 	mov ds, ax
 	mov cx, 4
 	mov bx, 0
 	clc
 	Otro:
 	mov al, Dir1[bx]
 	adc al, Dir2[bx]
 	mov Resul[bx], al
 	inc bx
	loop Otro
 	mov ah, 4Ch
 	int 21h
END Inicio