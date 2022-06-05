//BEGIN PROGRAM

//BLOCK 
push 0
mv $sp $fp //Load $fp for initial block
// GLOBAL FIELDS ASG

//INITCALL
push $fp
li $a0 1
push $a0 
mv $fp $al //put in $al actual fp
push $al
jal Function1 //Initcall: jump to start of main

halt //exit program...

//FUNCTIONS

Function0: //f
mv $sp $fp
push $ra
//ite
mv $fp $al // DerExpNode n
lw $a0 1($al) //loads in $a0 the value in n
push $a0
li $a0 0
lw $a2 0($sp)
pop
eq $a0 $a2 $a0 // $a0 = $a2 == $a0
bc $a0 false0
//move u to x 
mv $fp $al 
lw $a0 3($al)
push $a0//emptying asset u 
li $a0 0 
sw $a0 3($al)
//updating value
mv $fp $al 
lw $al 0($al)
lw $a0 0($al)
lw $a2 0($sp)add $a0 $a2 $a0 
sw $a0 0($al)
b end1
false0:
//move u to x 
mv $fp $al 
lw $a0 3($al)
push $a0//emptying asset u 
li $a0 0 
sw $a0 3($al)
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
end1:

endFunction0:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 3
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function0

Function1: //main
mv $sp $fp
push $ra
//Call f call
push $fp
//Allocating space for 0 body params
//Allocating space for 2 assets
mv $fp $al  //f
lw $a0 1($al) //loads in $a0 the value in 2-th asset
push $a0 
li $a0 0
sw $a0 1($al)
mv $fp $al  //f
lw $a0 1($al) //loads in $a0 the value in 1-th asset
push $a0 
li $a0 0
sw $a0 1($al)
//Allocating space for 1 actual parameters
li $a0 0
push $a0 
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction
mv $fp $al //put in $al actual fp
lw $al 0($al)
lw $a0 0($al) //loads in $a0 the value in x
transfer $a0
li $a0 0 // Emptying the asset...
sw $a0 0($al)

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

