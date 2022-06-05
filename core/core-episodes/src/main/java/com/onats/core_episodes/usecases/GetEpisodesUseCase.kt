package com.onats.core_episodes.usecases

import com.onats.common.UseCase
import com.onats.core_episodes.models.Episode

typealias GetEpisodesUseCase = @JvmSuppressWildcards UseCase<List<Episode>>