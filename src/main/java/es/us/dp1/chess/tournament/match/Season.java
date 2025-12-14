package es.us.dp1.chess.tournament.match;


import java.time.LocalDate;
import java.util.List;

import es.us.dp1.chess.tournament.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "season")
public class Season extends BaseEntity {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    String name;

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    List<ChessMatch> matches;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    List<Rating> ratings;

    @ManyToOne
    private Community community;

    /*
    SELECT v.description               
    FROM Visit v
    WHERE v.pet.type = :petType        
    AND v.datetime BETWEEN :startDate AND :endDate
    GROUP BY v.description             
    ORDER BY COUNT(v.description) DESC 

    Ordena de mayor a menos enfermedades (description) segun el tipo de mascota y en una fecha concreta
    
    
    @Test
    public void someTest(){
        GroomingType gt1 = createGroomingType("1", true);
        GroomingType gt2 = createGroomingType("2", true);
        GroomingType gt3 = createGroomingType("3", false);
        GroomingType gt4 = createGroomingType("4", false);

        GroomingPackage gp1 = createGroomingPackage(43.99,
        List.of(gt1, gt2));
        GroomingPackage gp5 = createGroomingPackage(44.00,
        List.of(gt1, gt2));
        GroomingPackage gp2 = createGroomingPackage(19.99,
        List.of(gt3, gt4));
        GroomingPackage gp6 = createGroomingPackage(20.00,
        List.of(gt3, gt4));
        GroomingPackage gp3 = createGroomingPackage(29.99,
        List.of(gt1, gt3));
        GroomingPackage gp7 = createGroomingPackage(30.0,
        List.of(gt1, gt3));
        GroomingPackage gp4 = createGroomingPackage(100.0,
        List.of());

        Assertions.assertThrows(WrongPriceException.class,
        () -> algorithm.validatePackageCost(gp1, 10, 20));

        Assertions.assertDoesNotThrow(
        ()-> algorithm.validatePackageCost(gp5, 10, 20));

        Assertions.assertThrows(WrongPriceException.class,
        () -> algorithm.validatePackageCost(gp2, 10, 20));

        Assertions.assertDoesNotThrow(
        ()-> algorithm.validatePackageCost(gp6, 10, 20));

        Assertions.assertThrows(WrongPriceException.class,
        () -> algorithm.validatePackageCost(gp3, 10, 20));

        Assertions.assertDoesNotThrow(
        ()-> algorithm.validatePackageCost(gp7, 10, 20));

        Assertions.assertDoesNotThrow(
        ()-> algorithm.validatePackageCost(gp4, 10, 20));
    }
    */
}
