//BEGIN PROGRAM

//BLOCK 
push 0
push 0
mv $sp $fp //Load $fp for initial block
// GLOBAL FIELDS ASG

// Field a
li $a0 1
sw $a0 0($fp)

//INITCALL
push $fp
li $a0 10
push $a0 
li $a0 1
push $a0 
mv $fp $al //put in $al actual fp
push $al
jal Function2 //Initcall: jump to start of main

halt //exit program...

//FUNCTIONS

Function0: //g
mv $sp $fp
push $ra
//ite
mv $fp $al // DerExpNode a
lw $a0 1($al) //loads in $a0 the value in a
push $a0
li $a0 0
lw $a2 0($sp)
pop
eq $a0 $a2 $a0 // $a0 = $a2 == $a0
bc $a0 false0
li $a0 0

b endFunction0
b end1
false0:
end1:

endFunction0:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 1
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function0

Function1: //f
mv $sp $fp
push $ra
//Call g call
push $fp
//Allocating space for 0 body params
//Allocating space for 0 assets
//Allocating space for 1 actual parameters
mv $fp $al // DerExpNode pippetta
lw $a0 1($al) //loads in $a0 the value in pippetta
push $a0 
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction
print $a0

endFunction1:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 1
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function1

Function2: //main
mv $sp $fp
push $ra
mv $fp $al // DerExpNode z
lw $a0 2($al) //loads in $a0 the value in z
print $a0
//Call f call
push $fp
//Allocating space for 0 body params
//Allocating space for 0 assets
//Allocating space for 1 actual parameters
li $a0 10
push $a0 
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function1//jump to start of function and put in $ra next instruction
mv $fp $al //put in $al actual fp
lw $a0 2($al) //loads in $a0 the value in z
transfer $a0
li $a0 0 // Emptying the asset...
sw $a0 2($al)

endFunction2:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 2
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function2

