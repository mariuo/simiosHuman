package com.mcamelo.simiosHuman.resources;

import com.mcamelo.simiosHuman.dtos.DnaRequest;
import com.mcamelo.simiosHuman.dtos.DnaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DnaValidation extends InfoAPI {
    @Operation(summary = "Return all dna's saved.",

            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(
                                                    name = "Success scenery",
                                                    value =
                                                            "[\n" +
                                                                    "  {\n" +
                                                                    "    \"id\": 1,\n" +
                                                                    "    \"sequence\": \"[CTGAGA, CTATGA, TATTGA, AGAGGA, CCCCTA, TGAAAA]\",\n" +
                                                                    "    \"dnaType\": \"SIMIOS\"\n" +
                                                                    "  },\n" +
                                                                    "  {\n" +
                                                                    "    \"id\": 2,\n" +
                                                                    "    \"sequence\": \"[CTGAGA, CTATGA, TATTGA, AGAGGA, CCCCTA, TGAAAA]\",\n" +
                                                                    "    \"dnaType\": \"SIMIOS\"\n" +
                                                                    "  },\n" +
                                                                    "  {\n" +
                                                                    "    \"id\": 3,\n" +
                                                                    "    \"sequence\": \"[ATGCGA, CAGTGC, TTATTT, AGACGG, GCGTCA, GCGTCA]\",\n" +
                                                                    "    \"dnaType\": \"HUMAN\"\n" +
                                                                    "  },\n" +
                                                                    "  {\n" +
                                                                    "    \"id\": 4,\n" +
                                                                    "    \"sequence\": \"[ATGCGA, CAGTGC, TTATTT, AGACGG, GCGTCA, GCGTCA]\",\n" +
                                                                    "    \"dnaType\": \"HUMAN\"\n" +
                                                                    "  }\n" +
                                                                    "]"
                                            )
                                    })
                    ),
                    @ApiResponse(responseCode = "408", description = "When the request failed because a timeout",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DnaResponse.class))),
                    @ApiResponse(responseCode = "500", description = "When happen intern error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DnaResponse.class))),
                    @ApiResponse(responseCode = "502", description = "When any request return null",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DnaResponse.class)))
            }
    )
    public ResponseEntity<List<DnaResponse>> findAll();

    @Operation(summary = "Validated the type of DNA.",

            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(
                                                    name = "Success scenery, DNA Simios",
                                                    value =
                                                            "{\n" +
                                                                    "  \"dna\": [\n" +
                                                                    "   \"CTGAGA\", \"CTATGA\", \"TATTGA\", \"AGAGGA\", \"CCCCTA\", \"TGAAAA\"\n" +
                                                                    "  ]\n" +
                                                                    "}"
                                            )
                                    })
                    ),
                    @ApiResponse(responseCode = "403", description = "When the DNA is Human. Forbidden.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DnaRequest.class))),
                    @ApiResponse(responseCode = "400", description = "When happen any validated error. Matrix NULL or Bad format.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DnaRequest.class))),
                    @ApiResponse(responseCode = "502", description = "When any request return null",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DnaRequest.class)))
            }
    )
    public ResponseEntity<Void> validateDna(@RequestBody DnaRequest dnaRequest);



}
