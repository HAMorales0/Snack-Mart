
The vending machine dispenses beverages, candy, chips, and gum. Each vending machine item has a Name and a Price.
The main menu displays when the software runs, presenting the following options:
    
    > (1) Display Vending Machine Items
    > (2) Purchase
    > (3) Exit
    
The vending machine reads its inventory from an input file when the vending machine
starts. The vending machine is automatically restocked each time the application runs.
When the customer selects "(1) Display Vending Machine Items", they're presented
with a list of all items in the vending machine with its quantity remaining:
  - Each vending machine product has a slot identifier and a purchase price.
  - Each slot in the vending machine has enough room for 5 of that product.
  - Every product is initially stocked to the maximum amount.
When the customer selects "(2) Purchase", they're guided through the purchasing
process menu:
    
    Ex. Current Money Provided: $2.00
    
    (1) Feed Money
    (2) Select Product
    (3) Finish Transaction
    
The purchase process flow is as follows:
 1. Selecting "(1) Feed Money" allows the customer to repeatedly feed money into the
    machine in whole dollar amounts.
 2. Selecting "(2) Select Product" allows the customer to select a product to
    purchase.
     - Show the list of products available and allow the customer to enter
       a code to select an item.
     - If the product code doesn't exist, the vending machine informs the customer and returns them
       to the Purchase menu.
     - If a product is currently sold out, the vending machine informs the customer and returns them to the
       Purchase menu.
     - If a customer selects a valid product, it's dispensed to the customer.
     - Dispensing an item prints the item name, cost, and the money
       remaining. Dispensing also returns a message:
      - All chip items print "Crunch Crunch, Yum!"
      - All candy items print "Munch Munch, Yum!"
      - All drink items print "Glug Glug, Yum!"
      - All gum items print "Chew Chew, Yum!"
      - After the machine dispenses the product, the machine updates its balance
        accordingly and return the customer to the Purchase menu. 
  3. Selecting "(3) Finish Transaction" allows the customer to complete the
    transaction.
      - The machine's current balance updates to $0 remaining.
    4. After completing their purchase, the user returns to the "Main" menu to
    continue using the vending machine.
The vending machine logs all transactions to prevent theft from the vending machine.
   - Each purchase must generate a line in a file called `Log.txt`.
   
        01/01/2019 12:00:00 PM FEED MONEY: $5.00 $5.00 
        01/01/2019 12:00:15 PM FEED MONEY: $5.00 $10.00 
        01/01/2019 12:00:20 PM Crunchie B4 $1.75 $8.25 
        01/01/2019 12:01:25 PM Cowtales B2 $1.50 $6.75 
        01/01/2019 12:01:35 PM GIVE CHANGE: $6.75 $0.00
       
