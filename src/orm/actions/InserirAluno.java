package orm.actions;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.modelo.Aluno;

public class InserirAluno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o e-mail do aluno: ");
        String email = scanner.nextLine();

        System.out.print("Digite o CPF do aluno: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite a data de nascimento do aluno (formato: dd/MM/yyyy): ");
        String dataNascimento = scanner.nextLine();

        System.out.print("Digite a naturalidade do aluno: ");
        String naturalidade = scanner.nextLine();

        System.out.print("Digite o endereço do aluno: ");
        String endereco = scanner.nextLine();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaescolar");
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();

            Aluno aluno = new Aluno(nome, email, cpf, dataNascimento, naturalidade, endereco);

            manager.persist(aluno);

            manager.getTransaction().commit();

            System.out.println("Aluno inserido com sucesso!");

        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
            factory.close();
            scanner.close();
        }
    }
}
