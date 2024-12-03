package com.mcamelo.simiosHuman.resources;

import com.mcamelo.simiosHuman.dtos.StatsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface Stats extends InfoAPI{

    @Operation(summary = "Return all DNA Statistc.",

            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(
                                                    name = "Success scenery",
                                                    value =
                                                            "{\n" +
                                                                    "  \"count_mutant_dna\": 0,\n" +
                                                                    "  \"count_human_dna\": 0,\n" +
                                                                    "  \"score\": 0\n" +
                                                                    "}"
                                            )
                                    })
                    ),
                    @ApiResponse(responseCode = "408", description = "When the request failed because a timeout",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StatsResponse.class))),
                    @ApiResponse(responseCode = "500", description = "When happen intern error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StatsResponse.class))),
                    @ApiResponse(responseCode = "502", description = "When any request return null",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StatsResponse.class)))
            }
    )
    public ResponseEntity<StatsResponse> getStats();
}
