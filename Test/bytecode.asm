//BEGIN PROGRAM

//BLOCK 
push 0
push 0
mv $sp $fp //Load $fp for initial block
// GLOBAL FIELDS ASG

//INITCALL
push $fp
li $a0 10
push $a0 
li $a0 9
push $a0 
li $a0 8
push $a0 
li $a0 7
push $a0 
li $a0 6
push $a0 
li $a0 5
push $a0 
li $a0 4
push $a0 
li $a0 3
push $a0 
li $a0 2
push $a0 
li $a0 1
push $a0 
mv $fp $al //put in $al actual fp
push $al
jal Function1 //Initcall: jump to start of main

halt //exit program...

//FUNCTIONS

Function0: //f1
mv $sp $fp
push $ra
//move a to x 
mv $fp $al 
lw $a0 1($al)
push $a0//emptying asset a 
li $a0 0 
sw $a0 1($al)
//updating value
mv $fp $al 
lw $al 0($al)
lw $a0 1($al)
lw $a2 0($sp)add $a0 $a2 $a0 
sw $a0 1($al)
//Call f1 call
push $fp
//Allocating space for 0 body params
//Allocating space for 10 assets
mv $fp $al  //f1
lw $a0 1($al) //loads in $a0 the value in 10-th asset
push $a0 
li $a0 0
sw $a0 1($al)
mv $fp $al  //f1
lw $a0 10($al) //loads in $a0 the value in 9-th asset
push $a0 
li $a0 0
sw $a0 10($al)
mv $fp $al  //f1
lw $a0 9($al) //loads in $a0 the value in 8-th asset
push $a0 
li $a0 0
sw $a0 9($al)
mv $fp $al  //f1
lw $a0 8($al) //loads in $a0 the value in 7-th asset
push $a0 
li $a0 0
sw $a0 8($al)
mv $fp $al  //f1
lw $a0 7($al) //loads in $a0 the value in 6-th asset
push $a0 
li $a0 0
sw $a0 7($al)
mv $fp $al  //f1
lw $a0 6($al) //loads in $a0 the value in 5-th asset
push $a0 
li $a0 0
sw $a0 6($al)
mv $fp $al  //f1
lw $a0 5($al) //loads in $a0 the value in 4-th asset
push $a0 
li $a0 0
sw $a0 5($al)
mv $fp $al  //f1
lw $a0 4($al) //loads in $a0 the value in 3-th asset
push $a0 
li $a0 0
sw $a0 4($al)
mv $fp $al  //f1
lw $a0 3($al) //loads in $a0 the value in 2-th asset
push $a0 
li $a0 0
sw $a0 3($al)
mv $fp $al  //f1
lw $a0 2($al) //loads in $a0 the value in 1-th asset
push $a0 
li $a0 0
sw $a0 2($al)
//Allocating space for 0 actual parameters
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction

endFunction0:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 10
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function0

Function1: //main
mv $sp $fp
push $ra
//Call f1 call
push $fp
//Allocating space for 0 body params
//Allocating space for 10 assets
mv $fp $al  //f1
lw $a0 10($al) //loads in $a0 the value in 10-th asset
push $a0 
li $a0 0
sw $a0 10($al)
mv $fp $al  //f1
lw $a0 9($al) //loads in $a0 the value in 9-th asset
push $a0 
li $a0 0
sw $a0 9($al)
mv $fp $al  //f1
lw $a0 8($al) //loads in $a0 the value in 8-th asset
push $a0 
li $a0 0
sw $a0 8($al)
mv $fp $al  //f1
lw $a0 7($al) //loads in $a0 the value in 7-th asset
push $a0 
li $a0 0
sw $a0 7($al)
mv $fp $al  //f1
lw $a0 6($al) //loads in $a0 the value in 6-th asset
push $a0 
li $a0 0
sw $a0 6($al)
mv $fp $al  //f1
lw $a0 5($al) //loads in $a0 the value in 5-th asset
push $a0 
li $a0 0
sw $a0 5($al)
mv $fp $al  //f1
lw $a0 4($al) //loads in $a0 the value in 4-th asset
push $a0 
li $a0 0
sw $a0 4($al)
mv $fp $al  //f1
lw $a0 3($al) //loads in $a0 the value in 3-th asset
push $a0 
li $a0 0
sw $a0 3($al)
mv $fp $al  //f1
lw $a0 2($al) //loads in $a0 the value in 2-th asset
push $a0 
li $a0 0
sw $a0 2($al)
mv $fp $al  //f1
lw $a0 1($al) //loads in $a0 the value in 1-th asset
push $a0 
li $a0 0
sw $a0 1($al)
//Allocating space for 0 actual parameters
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction
mv $fp $al //put in $al actual fp
lw $al 0($al)
lw $a0 1($al) //loads in $a0 the value in x
transfer $a0
li $a0 0 // Emptying the asset...
sw $a0 1($al)

endFunction1:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 10
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function1

