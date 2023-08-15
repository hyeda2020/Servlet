package hello.servlet.domain.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 *  동시성 문제가 고려되어 있지 않음.
 *  실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;
    private static final MemberRepository instance = new MemberRepository(); // 싱글톤 패턴을 위한 전용 인스턴스

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 영향이 가지 않게끔 하기 위해 새로 생성한 리스트에 담아서 리턴
    }

    public void clearStore() {
        store.clear();
    }
}
