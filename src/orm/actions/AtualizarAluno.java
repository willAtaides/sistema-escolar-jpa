package orm.actions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

import orm.modelo.Aluno;

public class AtualizarAluno {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaescolar");
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do Aluno que deseja atualizar: ");
        long alunoId = scanner.nextLong();

        Aluno aluno = manager.find(Aluno.class, alunoId);

        if (aluno != null) {
            System.out.print("Digite o novo nome: ");
            aluno.setNome(scanner.next());

            System.out.print("Digite o novo e-mail: ");
            aluno.setEmail(scanner.next());

            System.out.print("Digite o novo CPF: ");
            aluno.setCpf(scanner.next());

            System.out.print("Digite a nova data de nascimento (dd/MM/yyyy): ");
            aluno.setDataNascimento(scanner.next());

            System.out.print("Digite a nova naturalidade: ");
            aluno.setNaturalidade(scanner.next());

            System.out.print("Digite o novo endereço: ");
            aluno.setEndereco(scanner.next());

            manager.getTransaction().begin();

            manager.merge(aluno);

            manager.getTransaction().commit();

            System.out.println("Aluno atualizado com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }

        scanner.close();
        manager.close();
        factory.close();
    }
}
