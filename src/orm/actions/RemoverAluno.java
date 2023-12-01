package orm.actions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

import orm.modelo.Aluno;

public class RemoverAluno {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaescolar");
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do Aluno que deseja remover: ");
        long alunoId = scanner.nextLong();

        Aluno aluno = manager.find(Aluno.class, alunoId);

        if (aluno != null) {
            manager.getTransaction().begin();

            manager.remove(aluno);

            manager.getTransaction().commit();

            System.out.println("Aluno removido com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }

        scanner.close();
        manager.close();
        factory.close();
    }
}
