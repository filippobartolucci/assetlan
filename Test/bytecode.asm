//BEGIN PROGRAM

//BLOCK 
push 0 mv $sp $fp //Load $fp for initial block
// GLOBAL FIELDS ASG
push 0

//INITCALL
push $fp
li $a0 1
push $a0 
mv $fp $al //put in $al actual fp
push $al
jal Function0 //Initcall: jump to start of f

halt //exit program...

//FUNCTIONS

Function0: //f
mv $sp $fp
push $ra
//ite
li $a0 1
bc $a0 false0
mv $fp $al //put in $al actual fp
lw $a0 1($al) //loads in $a0 the value in a
transfer $a0
li $a0 0 // Emptying the asset...
sw $a0 1($al)
b end1
false0:
end1:

endFunction0:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 0
addi $sp $sp 1
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function0

