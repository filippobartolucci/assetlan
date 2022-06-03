//BEGIN PROGRAM
li $a0 3
push $a0 
mv $sp $fp //Load new $fp
push $fp
mv $fp $al //put in $al actual fp
push $al
jal Function0 //Initcall: jump to start of f
halt

//Functions
Function0: //f
mv $sp $fp
push $ra
mv $fp $al // DerExpNode x
lw $al 0($al)
lw $a0 0($al) //loads in $a0 the value in x
print $a0
endFunction0:
subi $sp $fp 1 //Restore stack pointer as before block creation in a void function without return 
lw $fp 0($fp) //Load old $fp pushed 
lw $ra 0($sp)
pop
addi $sp $sp 0
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function0
