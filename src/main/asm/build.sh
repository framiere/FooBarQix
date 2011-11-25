rm -f *.o
rm -f foobarqix
/usr/local/Cellar/nasm/2.09.10/bin/nasm -g -f macho foobarqix.asm
/usr/local/Cellar/nasm/2.09.10/bin/nasm -g -f macho methods.asm
gcc -arch i386 -o foobarqix  methods.o foobarqix.o
chmod +x foobarqix
./foobarqix
echo returned $?
