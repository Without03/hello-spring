package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0 1 2 key값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // sequence 값 1증가
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이어도 감싸서 반환 가능
    }
    // Optional.of() : null이 아닐때 생성. 만약 null을 저장하려고 하면 npe 발생

    /*
    1. store.values()

    store는 Map 타입 (예: Map<Long, Member>)
    values()는 Map의 모든 값들을 Collection<Member> 형태로 반환
    예: [Member1, Member2, Member3, ...]

    2. .stream()

    Collection을 Stream으로 변환
    Stream은 데이터를 함수형으로 처리할 수 있게 해주는 API
    Stream<Member> 생성

    3. .filter(member -> member.getName().equals(name))

    중간 연산: 조건에 맞는 요소만 걸러냄
    member -> member.getName().equals(name): 람다 표현식

    각 member에 대해 이름이 name과 일치하는지 확인
    true인 요소만 통과



    4. .findAny()

    최종 연산: Stream을 종료하고 결과 반환
    필터를 통과한 요소 중 아무거나 하나 반환
    반환 타입: Optional<Member>

    요소를 찾으면: Optional.of(member)
    못 찾으면: Optional.empty()
     */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // optional에 null이 포함되어 없으면 empty 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() { // test시 repository clear
        store.clear();
    }
}
