.model small
.stack 100h
.data
	Texto DB "Please enter a maximum 20 character sentence: ",13,10,'$'
 	MaximoMas1 DB 21
 	CaracteresLeidos DB 0
 	Cadena DB 21 DUP (0)
 	Otralinea DB 13,10,'$'
.code
Inicio:
 	mov ax, @data
 	mov ds, ax
 	mov ah, 9
 	lea dx, Texto
 	int 21h

 	mov ah, 0Ah
 	lea dx, MaximoMas1
 	int 21h

 	mov ah, 9
 	lea dx, Otralinea
 	int 21h
        
    sub cx, cx
 	mov cl, MaximoMas1
 	xor SI, SI
 	mov ah, 2
 	bucle:
 	mov dl, Cadena[SI]
 	int 21h
 	inc SI   
 	loop bucle
 	mov ah, 4Ch
 	int 21h
END Inicio