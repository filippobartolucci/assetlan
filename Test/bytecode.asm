//BEGIN PROGRAM

//BLOCK 
push 0
mv $sp $fp //Load $fp for initial block
// GLOBAL FIELDS ASG

//INITCALL
push $fp
li $a0 0 
 push $a0 
li $a0 10
push $a0 
mv $fp $al //put in $al actual fp
push $al
jal Function1 //Initcall: jump to start of main

halt //exit program...

//FUNCTIONS

Function0: //fibonacci
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
mv $fp $al //put in $al actual fp
lw $a0 2($al) //loads in $a0 the value in a
transfer $a0
li $a0 0 // Emptying the asset...
sw $a0 2($al)
li $a0 0

b endFunction0
b end1
false0:
end1:
//ite
mv $fp $al // DerExpNode n
lw $a0 1($al) //loads in $a0 the value in n
push $a0
li $a0 1
lw $a2 0($sp)
pop
eq $a0 $a2 $a0 // $a0 = $a2 == $a0
bc $a0 false2
//move a to z 
mv $fp $al 
lw $a0 2($al)
push $a0//emptying asset a 
li $a0 0 
sw $a0 2($al)
//updating value
mv $fp $al 
lw $al 0($al)
lw $a0 0($al)
lw $a2 0($sp)add $a0 $a2 $a0 
sw $a0 0($al)
li $a0 1

b endFunction0
b end3
false2:
end3:
//move a to z 
mv $fp $al 
lw $a0 2($al)
push $a0//emptying asset a 
li $a0 0 
sw $a0 2($al)
//updating value
mv $fp $al 
lw $al 0($al)
lw $a0 0($al)
lw $a2 0($sp)add $a0 $a2 $a0 
sw $a0 0($al)
//Call fibonacci call
push $fp
//Allocating space for 0 body params
//Allocating space for 1 assets
mv $fp $al  //fibonacci
lw $al 0($al)
lw $a0 0($al) //loads in $a0 the value in 1-th asset
push $a0 
li $a0 0
sw $a0 0($al)
//Allocating space for 1 actual parameters
mv $fp $al // DerExpNode n
lw $a0 1($al) //loads in $a0 the value in n
push $a0
li $a0 1
lw $a2 0($sp)
pop
sub $a0 $a2 $a0 // a0 = t1-a0
push $a0 
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction
push $a0
//Call fibonacci call
push $fp
//Allocating space for 0 body params
//Allocating space for 1 assets
mv $fp $al  //fibonacci
lw $al 0($al)
lw $a0 0($al) //loads in $a0 the value in 1-th asset
push $a0 
li $a0 0
sw $a0 0($al)
//Allocating space for 1 actual parameters
mv $fp $al // DerExpNode n
lw $a0 1($al) //loads in $a0 the value in n
push $a0
li $a0 2
lw $a2 0($sp)
pop
sub $a0 $a2 $a0 // a0 = t1-a0
push $a0 
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction
lw $a2 0($sp)
pop
add $a0 $a2 $a0 // a0 = t1+a0

b endFunction0

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

Function1: //main
mv $sp $fp
push $ra
//move a to z 
mv $fp $al 
lw $a0 1($al)
push $a0//emptying asset a 
li $a0 0 
sw $a0 1($al)
//updating value
mv $fp $al 
lw $al 0($al)
lw $a0 0($al)
lw $a2 0($sp)add $a0 $a2 $a0 
sw $a0 0($al)

// Asg n
//Call fibonacci call
push $fp
//Allocating space for 0 body params
//Allocating space for 1 assets
mv $fp $al  //fibonacci
lw $al 0($al)
lw $a0 0($al) //loads in $a0 the value in 1-th asset
push $a0 
li $a0 0
sw $a0 0($al)
//Allocating space for 1 actual parameters
li $a0 3
push $a0 
mv $fp $al // calling function...
lw $al 0($al) //go up to chain
push $al
jal Function0//jump to start of function and put in $ra next instruction
mv $fp $al //put in $al actual fp
sw $a0 2($al) //put %a0 in Id n

endFunction1:
subi $sp $fp 1 
lw $fp 0($fp) 
lw $ra 0($sp)
pop
addi $sp $sp 2
pop
lw $fp 0($sp)
pop
jr $ra
//END OF Function1

