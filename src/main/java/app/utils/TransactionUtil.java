package app.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.function.Supplier;

public class TransactionUtil {

    public static void execute(EntityManager em, Runnable action) { // this metode is for run code in a transaktion.
        EntityTransaction transaction = em.getTransaction(); // this retrieving a transaction from the EntityManager.

        try {
            transaction.begin(); // begin

            action.run(); // run the code, there was sent in.

            transaction.commit(); //save chances in the database.
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public static <T> T executeWithResult(EntityManager em, Supplier<T> action) { // this metode is for running the code in a transaction and return the result.
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            T result = action.get(); // run and save the result

            transaction.commit();

            return result;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
