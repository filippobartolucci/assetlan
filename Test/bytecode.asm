//BEGIN PROGRAM
mv $sp $fp //Load new $fp
push $fp
//Function f call
push $fp
//Allocating space for 0 body params
//Allocating space for 0 assets
//Allocating space for 0 actual parameters
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction
push $a0 
push $a0 
mv $fp $al //put in $al actual fp
push $al
jal Function1 //Initcall: jump to start of g
halt

//Functions
Function0: //f
mv $sp $fp
push $ra
li $a0 1

b endFunction0
endFunction0:
subi $sp $fp 1 //Restore stack pointer as before block creation in a void function without return 
lw $fp 0($fp) //Load old $fp pushed 
lw $ra 0($sp)
pop
addi $sp $sp 0
addi $sp $sp 0
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function0
Function1: //g
mv $sp $fp
push $ra
endFunction1:
subi $sp $fp 1 //Restore stack pointer as before block creation in a void function without return 
lw $fp 0($fp) //Load old $fp pushed 
lw $ra 0($sp)
pop
addi $sp $sp 0
addi $sp $sp 1
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function1
