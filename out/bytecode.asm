//BEGIN PROGRAM

//BLOCK 
push 0 li $a0 0
push 0
mv $sp $fp //Load $fp for initial block
// GLOBAL FIELDS ASG

// Field o
li $a0 0
mv $fp $al //put in $al actual fp
sw $a0 0($al)
push 0

//INITCALL
push $fp
li $a0 0 
push $a0 
li $a0 3
push $a0 
li $a0 2
push $a0 
li $a0 1
push $a0 
mv $fp $al //put in $al actual fp
push $al
jal Function1 //Initcall: jump to start of g

halt //exit program...

//FUNCTIONS

Function0: //f
mv $sp $fp
push $ra
//Call f call
push $fp
//Allocating space for 0 body params
//Allocating space for 0 assets
//Allocating space for 2 actual parameters
li $a0 2
push $a0 
li $a0 1
push $a0 
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction

endFunction0:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 0
addi $sp $sp 2
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function0

Function1: //g
mv $sp $fp
push $ra

// Field x
li $a0 1
mv $fp $al //put in $al actual fp
sw $a0 4($al)
mv $fp $al // DerExpNode a
lw $a0 1($al) //loads in $a0 the value in a
print $a0
mv $fp $al // DerExpNode b
lw $a0 2($al) //loads in $a0 the value in b
print $a0
mv $fp $al // DerExpNode c
lw $a0 3($al) //loads in $a0 the value in c
print $a0
//Call f call
push $fp
//Allocating space for 0 body params
//Allocating space for 0 assets
//Allocating space for 2 actual parameters
li $a0 1
multi $a0 $a0 -1 //negate
push $a0 
li $a0 1
multi $a0 $a0 -1 //negate
push $a0 
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction
mv $fp $al // DerExpNode o
lw $al 0($al)
lw $a0 0($al) //loads in $a0 the value in o
print $a0
mv $fp $al // DerExpNode x
lw $a0 4($al) //loads in $a0 the value in x
print $a0
mv $fp $al //put in $al actual fp
lw $a0 1($al) //loads in $a0 the value in a
transfer $a0
li $a0 0 // Emptying the asset...
sw $a0 1($al)
mv $fp $al //put in $al actual fp
lw $a0 2($al) //loads in $a0 the value in b
transfer $a0
li $a0 0 // Emptying the asset...
sw $a0 2($al)
mv $fp $al //put in $al actual fp
lw $a0 3($al) //loads in $a0 the value in c
transfer $a0
li $a0 0 // Emptying the asset...
sw $a0 3($al)

endFunction1:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 0
addi $sp $sp 4
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function1

