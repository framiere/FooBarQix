all: clean compile run

run: 
	./foobarqix

compile:
	nasm -g -f macho foobarqix.asm
	nasm -g -f macho methods.asm
	gcc -arch i386 -o foobarqix  *.o 

clean:
	rm -f foobarqix *.o #.*
