package com.codestates.backup;

import com.codestates.backup.entity.BackupMember;
import com.codestates.backup.repository.BackupMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BackupMemberService {
    private final BackupMemberRepository backupMemberRepository;

    public BackupMemberService(BackupMemberRepository backupMemberRepository) {
        this.backupMemberRepository = backupMemberRepository;
    }

    @Transactional
    public void createBackupMember(BackupMember backupMember) {
        backupMemberRepository.save(backupMember);

        throw new RuntimeException("multi datasource rollback test"); // 회원 정보 저장 중, 예외 발생 시뮬레이션을 위함
    }
}
