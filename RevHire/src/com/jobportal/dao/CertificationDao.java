package com.jobportal.dao;

import com.jobportal.dto.Certification;
import java.util.List;

public interface CertificationDao {

    boolean addCertification(Certification cert);

    List<Certification> getCertificationsByJobSeeker(int jobSeekerId);

    boolean deleteCertification(int certificationId);
}