import jdk.jfr.DataAmount

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // Welcome to your banking system program.
    println("Welcome to your banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    // Initialize variables for account type and user choice.
    var accountType = ""
    var userChoice: Int

    // Loop until a valid account type is selected.
    while (accountType == "") {
        print("Choose an option (1, 2 or 3): ")
        userChoice = readLine()?.toIntOrNull() ?: 0
        println("The selected option is ${userChoice}.")

        when (userChoice) {
            1 -> accountType = "debit"
            2 -> accountType = "credit"
            3 -> accountType = "checking"
            else -> continue
        }
    }

    // Print the created account type.
    println("You have created a ${accountType} account.")

    // Initialize account balance and transferred money.
    var accountBalance = (0..1000).random()
    println("The checking balance is ${accountBalance} dollars.")
    print("Enter the amount you want to transfer: ")
    val money = readLine()?.toIntOrNull() ?: 0
    println("The amount you transferred is ${money} dollars.")

    // Define functions for withdrawing, depositing, and transferring money.
    fun withdraw(amount: Int): Int {
        accountBalance -= amount
        println("You successfully withdrew ${amount} dollars. The checking balance is ${accountBalance} dollars.")
        return amount
    }

    fun debitWithdraw(amount: Int): Int {
        // Handle special cases for debit account withdrawal.
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!")
            return accountBalance
        } else if (amount > accountBalance) {
            println("Not enough money on this account! The checking balance is ${accountBalance} dollars.")
            return 0
        } else {
            return withdraw(amount)
        }
    }

    fun deposit(amount: Int): Int {
        // Deposit money into the account.
        accountBalance += amount
        println("You successfully deposited ${amount} dollars. The checking balance is ${accountBalance} dollars.")
        return amount
    }

    fun creditDeposit(amount: Int): Int {
        // Handle special cases for credit account deposit.
        if (accountBalance == 0) {
            println("This account is completely paid off! Do not deposit money!")
            return accountBalance
        } else if (accountBalance + amount > 0) {
            println("Deposit failed, you tried to pay off an amount greater than the credit balance. The checking balance is ${accountBalance} dollars.")
            return 0
        } else if (amount == -accountBalance) {
            accountBalance = 0
            println("You have paid off this account!")
            return amount
        } else {
            return deposit(amount)
        }
    }

    fun transfer(mode: String) {
        // Perform money transfer based on the specified mode.
        val transferAmount: Int

        when (mode) {
            "withdraw" -> {
                if (accountType == "debit") {
                    transferAmount = debitWithdraw(money)
                } else {
                    transferAmount = withdraw(money)
                }
                println("The amount you withdrew is ${transferAmount} dollars.")
            }
            "deposit" -> {
                if (accountType == "credit") {
                    transferAmount = creditDeposit(money)
                } else {
                    transferAmount = deposit(money)
                }
                println("The amount you deposited is ${transferAmount} dollars.")
            }
            else -> return
        }
    }

    // Initialize variables for system status and user option.
    var isSystemOpen = true
    var option: Int

    // Main loop for user interactions with the banking system.
    while (isSystemOpen == true) {
        println("What would you like to do?")
        println("1. Check bank account balance")
        println("2. Withdraw money")
        println("3. Deposit money")
        println("4. Close the system")
        print("Which option do you choose? (1, 2, 3 or 4): ")

        // Randomly select an option for demonstration purposes.
        option = readLine()?.toIntOrNull() ?: 0
        println("The selected option is ${option}.")

        // Process user-selected option.
        when (option) {
            1 -> println("The current balance is ${accountBalance} dollars.")
            2 -> transfer("withdraw")
            3 -> transfer("deposit")
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue
        }
    }
}

