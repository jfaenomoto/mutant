package net.jfaenomoto.mutant.repository;

import net.jfaenomoto.mutant.domain.MappedDNA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappedDNARepository extends CrudRepository<MappedDNA, Long> {

    MappedDNA findByHash(String hash);

    Long countByIsMutant(boolean isMutant);

}
