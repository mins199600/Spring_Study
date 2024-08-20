package com.example.springpractice.service;
import com.example.springpractice.domain.Member;
import com.example.springpractice.repository.MemberRepository;
import com.example.springpractice.repository.MemoryMemberRespository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
        public long join(Member member) {
            //비즈니스 로직중에 같은 이름이 있는 중복 회원X
            validateDuplicateMember(member);    //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        }

        private void validateDuplicateMember(Member member){
            memberRepository.findByName(member.getName())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원 입니다");
                    });
        }
        //전체 회원 조회
        public List<Member> findMembers() {
            return memberRepository.findAll();
        }

        public Optional<Member> findOne(Long memberid) {
            return memberRepository.findById(memberid);
        }
    }

