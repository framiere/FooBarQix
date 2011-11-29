all: clean compile run

run: 
	./foobarqix

compile:
	nasm -f macho foobarqix.asm
	gcc -gstabs -arch i386 -o foobarqix  foobarqix.o 

clean:
	rm -f foobarqix *.o #.*
