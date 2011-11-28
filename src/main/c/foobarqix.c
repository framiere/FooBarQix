#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define FOO_val 3
#define FOO_char '0' + FOO_val
#define FOO "Foo"
#define BAR_val 5
#define BAR_char '0' + BAR_val
#define BAR "Bar"
#define QIX_val 7
#define QIX_char '0' + QIX_val
#define QIX "Qix"

#define BUFFER_SIZE 20
#define VALUE_UP_TO 100
#define SUCCESS 0

char buffer[BUFFER_SIZE];
char number[BUFFER_SIZE];

char* foobarqix(int value) {
	// reset old state
	memset(buffer, '\0', BUFFER_SIZE);
	memset(number, '\0', BUFFER_SIZE);
	
	// get char version in buffer
	sprintf(number, "%d", value);
	
	// rule 1
	if ((value % FOO_val) == 0) {
		sprintf(buffer, "%s", FOO);
	}
	if ((value % BAR_val) == 0) {
		sprintf(buffer, "%s%s", buffer, BAR);
	}
	if ((value % QIX_val) == 0) {
		sprintf(buffer, "%s%s", buffer, QIX);
	}
	int numberLen = strlen(number);
	int i = 0;
	for(i = 0; i < numberLen; i ++) {
		switch(number[i]) {
			case FOO_char:
				sprintf(buffer, "%s%s", buffer, FOO);
				break;
			case BAR_char:
				sprintf(buffer, "%s%s", buffer, BAR);
				break;
			case QIX_char:
				sprintf(buffer, "%s%s", buffer, QIX);
				break;
		}
	}
	return strlen(buffer) == 0 ? number : buffer; 
}

int main() {
	int i = 1;
	for(i = 1; i <= VALUE_UP_TO; i ++) {
		printf("%s\n", foobarqix(i));
	}
	return SUCCESS;
}