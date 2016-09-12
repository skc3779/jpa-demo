package io.kangchun;

/**
 * Created by seokangchun on 2016. 9. 13..
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
        EntityManager em = emf.createEntityManager();
        //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            logic(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋

//            System.out.println("array to list : " + Stream.of(1,2,3,4,5).map(String::valueOf).collect(joining(",")));

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static Member getCreateMember(String name) {
        Member member = new Member();
        //@GeneratedValue(strategy = GenerationType.AUTO) 사용
        //member.setId(id);
        member.setUsername(name);
        member.setAge(2);
        member.setTemp("템프 데이터");
        member.setRoleType(RoleType.ADMIN);
        //member.setCreatedDate(Date.from(new Date().toInstant()));
        member.setCreatedDate(Date.from(Instant.now()));
        member.setLastModifiedDate(Date.from(Instant.now()));
        return member;
    }

    public static void logic(EntityManager em) {

        //등록
        Member member1 = getCreateMember("일이");
        Member member2 = getCreateMember("투이");
        Member member3 = getCreateMember("삼이");


        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        //한 건 조회
        Member findMember = em.find(Member.class, member3.getId());
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());
        System.out.println("mebers toString() :" +  member3.toString());

        //삭제
        //em.remove(member);

    }
}
