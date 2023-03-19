package Hammurabi;
import java.io.IOException;
import java.util.*;

public class Hammurabi {
	Random rand = new Random();
	Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		new Hammurabi().playGame();
	}

	void playGame() {
		int grainTotal = 3000;
		int population = 100;
		int currentYear = 1;
		int immigrants = 5;
		int acres = 1000;
		int bushelsPerAcre = 3;
		int grainEatenByRats = 200;
		int landValue = 19;
		int grain = grainTotal - grainEatenByRats;

		while (currentYear < 10) {
			printSummary(currentYear, immigrants, population, grain, grainTotal, bushelsPerAcre, grainEatenByRats, acres, landValue);
			askHowManyAcresToBuy(landValue, grain);
			askHowManyAcresToSell(acres);
			askHowMuchGrainToFeedPeople(grain);
			askHowManyAcresToPlant(acres, population, grain);
			currentYear++;
		}
		finalSummary();
	}

	public void printSummary(int currentYear, int immigrants, int population, int grain, int grainTotal, int bushelsPerAcre, int grainEatenByRats, int acres, int landValue) {
		System.out.println("O great Hammurabi!\n" +
				"You are in year " + currentYear + " of your ten year rule.\n" +
				"In the previous year " + (currentYear - 1) + " people starved to death.\n" +
				"in the previous year " + immigrants + " people entered the kingdom.\n" +
				"The population is now " + population + ".\n" +
				"We harvested " + grainTotal + " bushels at " + bushelsPerAcre + " bushels per acre.\n" +
				"Rats destroyed " + grainEatenByRats + " bushels, leaving " + grain + " bushels in storage.\n" +
				"The city owns " + acres + " acres of land.\n" +
				"Land is currently worth " + landValue + " bushels per acre." );
	}

	public void finalSummary() {
		
	}

	int askHowManyAcresToBuy(int price, int bushels) {
		int maxAcresYouCanAfford = bushels / price;
		boolean cameCorrect = false;
		int acresBought = 0;
		while(!cameCorrect) {
			System.out.println("\nHow many acres of land would you like to buy?");
			int acresWanted = scanner.nextInt();
			if (acresWanted < maxAcresYouCanAfford) {
				acresBought += acresWanted;
				cameCorrect = true;
			} else {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
			}
		}
		return acresBought;
	}

	int askHowManyAcresToSell(int acresOwned) {
		boolean cameCorrect = false;
		int acresSold = 0;
		while(!cameCorrect) {
			System.out.println("\nHow many acres of land would you like to sell?");
			int acresYouWantToSell = scanner.nextInt();
			if (acresYouWantToSell < acresOwned) {
				acresSold += acresYouWantToSell;
				cameCorrect = true;
			} else {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres left!");
			}
		}
		return acresSold;
	}
	int askHowMuchGrainToFeedPeople(int bushels) {
		boolean cameCorrect = false;
		int grainForFood = 0;
		while (!cameCorrect) {
			System.out.println("\nHow many acres of land would you like to feed the population with?");
			int edibleGrain = scanner.nextInt();
			if (edibleGrain < bushels) {
				grainForFood += edibleGrain;
				cameCorrect = true;
			} else {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
			}
		}
		return grainForFood;
	}

	int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
		boolean cameCorrect = false;
		int plantableAcresByWorkforce = population * 10;
		int plantableAcresByGrain = bushels / 2;
		int acresToPlant = 0;
		while (!cameCorrect) {
			System.out.println("\nHow many acres would you like to plant?");
			int acresDesiredToBePlanted = scanner.nextInt();
			if (acresDesiredToBePlanted > acresOwned) {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres left!");
			} else if (acresDesiredToBePlanted > plantableAcresByWorkforce) {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + population + " people!");
			} else if (acresDesiredToBePlanted > plantableAcresByGrain) {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
			} else {
				acresToPlant += acresDesiredToBePlanted;
				cameCorrect = true;
			}
		}
		return acresToPlant;
	}


	int plagueDeaths(int pop) {
		int deathPercent = rand.nextInt(pop * (15 / 100));
		//deaths += deathPercent;
		//population -= deathPercent;
		return deathPercent;
	}

	int starvationDeaths(int pop, int bushelsFedToPeople) {
		//population
		//bushels left after farming
		//if population * 20 > bushels left, return 0 deaths
		//else deathCount, bushelLeftPerPerson, return n deaths
		return 0;
	}

	boolean uprising(int pop, int howManyPeopleStarved) {
		//if starving population > 45% return true
		//else return false;
		return false;
	}

	int grainEatenByRats(int bushels) {
		return 0;
	}

	int newCostOfLand() {
		return 0;
	}

	int harvest(int acres, int bushelsUsedAsSeed) {
		return 0;
	}

	int newImmigrants(int pop, int acresOwned, int grain) {
		//starving
		return 0;
	}

}