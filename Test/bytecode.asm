//BEGIN PROGRAM

//BLOCK 
push 0
push 0
mv $sp $fp //Load $fp for initial block
// GLOBAL FIELDS ASG

//INITCALL
push $fp
li $a0 3
push $a0 
li $a0 2
push $a0 
mv $fp $al //put in $al actual fp
push $al
jal Function0 //Initcall: jump to start of main

halt //exit program...

//FUNCTIONS

Function0: //main
mv $sp $fp
push $ra
//move u to x 
mv $fp $al 
lw $a0 1($al)
push $a0//emptying asset u 
li $a0 0 
sw $a0 1($al)
//updating value
mv $fp $al 
lw $al 0($al)
lw $a0 0($al)
lw $a2 0($sp)add $a0 $a2 $a0 
sw $a0 0($al)
//move v to x 
mv $fp $al 
lw $a0 2($al)
push $a0//emptying asset v 
li $a0 0 
sw $a0 2($al)
//updating value
mv $fp $al 
lw $al 0($al)
lw $a0 0($al)
lw $a2 0($sp)add $a0 $a2 $a0 
sw $a0 0($al)
//ite
li $a0 1
bc $a0 false0
li $a0 1
print $a0
b end1
false0:
li $a0 2
print $a0
end1:
mv $fp $al //put in $al actual fp
lw $al 0($al)
lw $a0 0($al) //loads in $a0 the value in x
transfer $a0
li $a0 0 // Emptying the asset...
sw $a0 0($al)

endFunction0:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 2
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function0

