package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Salons;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAllSalonsQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonByIdQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonByEmailQuery;

import java.util.List;
import java.util.Optional;

/**
 * @summary
 * Interface for query operations on the Salons aggregate.
 * Defines contracts for retrieving salon data.
 */
public interface SalonsQueryService {

    /**
     * Handle retrieving all salons.
     * @param query The query with no parameters.
     * @return List of salons.
     */
    List<Salons> handle(GetAllSalonsQuery query);

    /**
     * Handle retrieving a salon by its ID.
     * @param query Query containing the salon ID.
     * @return Optional salon.
     */
    Optional<Salons> handle(GetSalonByIdQuery query);

    /**
     * Handle retrieving a salon by its email.
     * @param query Query containing the email.
     * @return Optional salon.
     */
    Optional<Salons> handle(GetSalonByEmailQuery query);
}
