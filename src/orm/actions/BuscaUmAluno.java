package orm.actions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

import orm.modelo.Aluno;

public class BuscaUmAluno {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaescolar");
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a letra inicial do nome do Aluno que deseja buscar: ");
        String initialLetter = scanner.nextLine().toUpperCase(); 

        String jpql = "SELECT a FROM Aluno a WHERE UPPER(SUBSTRING(a.nome, 1, 1)) = :initialLetter";
        TypedQuery<Aluno> query = manager.createQuery(jpql, Aluno.class);
        query.setParameter("initialLetter", initialLetter);

        List<Aluno> alunos = query.getResultList();


        if (!alunos.isEmpty()) {
            for (Aluno aluno : alunos) {
                System.out.println("ID: " + aluno.getId());
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("E-mail: " + aluno.getEmail());
                System.out.println("CPF: " + aluno.getCpf());
                System.out.println("Data de Nascimento: " + aluno.getDataNascimento());
                System.out.println("Naturalidade: " + aluno.getNaturalidade());
                System.out.println("Endereço: " + aluno.getEndereco());
                System.out.println("----------------------");
            }
        } else {
            System.out.println("Nenhum Aluno encontrado com a letra inicial especificada.");
        }

        scanner.close();
        manager.close();
        factory.close();
    }
}
