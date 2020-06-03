package de.idrinth.endlessspace2.modvalidator.data;

import de.idrinth.endlessspace2.modvalidator.SimulationDescriptorReference;
import java.util.HashSet;

public class SimulationReferenceListHelper {
    // @todo this should be gathered from xsds automatically
    public static HashSet<SimulationDescriptorReference> provide() {
        HashSet<SimulationDescriptorReference> references = new HashSet<>();
        //HeroSkillDefinition.xsd
        references.add(new SimulationDescriptorReference("HeroSkillDefinition.xsd", "HeroSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("HeroSkillDefinition.xsd", "SenateSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("HeroSkillDefinition.xsd", "ShipSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("HeroSkillDefinition.xsd", "SystemSimulationDescriptorReference", "Name"));
        //PopulationModifiersTrait.xsd
        references.add(new SimulationDescriptorReference("PopulationModifiersTrait.xsd", "SimulationDescriptorReference", "Name"));
        //AcademyStateDefinition[DLC4].xsd
        references.add(new SimulationDescriptorReference("AcademyStateDefinition[DLC4].xsd", "SimulationDescriptorReference", "Name"));
        //AcademyStateDefinition.xsd
        references.add(new SimulationDescriptorReference("AcademyStateDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //ZoneEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("ZoneEffectDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //DisplacementEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("DisplacementEffectDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //AnomalyDefinition.xsd
        references.add(new SimulationDescriptorReference("AnomalyDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //BasicModuleDefinition.xsd
        references.add(new SimulationDescriptorReference("BasicModuleDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //BattleEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("BattleEffectDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //CitadelDefinition.xsd
        references.add(new SimulationDescriptorReference("CitadelDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //ConstellationOwnershipBonusDefinition.xsd
        references.add(new SimulationDescriptorReference("ConstellationOwnershipBonusDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //AnomalyReductionDefinition.xsd
        references.add(new SimulationDescriptorReference("AnomalyReductionDefinition.xsd", "InProgressSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("AnomalyReductionDefinition.xsd", "QueuedSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("AnomalyReductionDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //Droplist.xsd
        references.add(new SimulationDescriptorReference("ZoneEffectDefinition.xsd", "Unlock", "SimulationDescriptorReference"));
        //ConstructibleElement.xsd
        references.add(new SimulationDescriptorReference("ConstructibleElement.xsd", "InProgressSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("ConstructibleElement.xsd", "QueuedSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("ConstructibleElement.xsd", "SimulationDescriptorReference", "Name"));
        //CuriosityDefinition.xsd
        references.add(new SimulationDescriptorReference("CuriosityDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //DiplomaticAbilityDefinition.xsd
        references.add(new SimulationDescriptorReference("DiplomaticAbilityDefinition.xsd", "SimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("DiplomaticAbilityDefinition.xsd", "DiplomaticEmpireSimulationDescriptorReference", "Name"));
        //DiplomaticFactionPactEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("DiplomaticFactionPactEffectDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //EncounterTargetingRangeDefinition.xsd
        references.add(new SimulationDescriptorReference("EncounterTargetingRangeDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //EncounterSequenceDefinition.xsd
        references.add(new SimulationDescriptorReference("EncounterSequenceDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //EncounterPlayDefinition.xsd
        references.add(new SimulationDescriptorReference("EncounterPlayDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //AcademyFaction.xsd
        references.add(new SimulationDescriptorReference("AcademyFaction.xsd", "SimulationDescriptorReference", "Name"));
        //TraitorBonusThresholdDefinition.xsd
        references.add(new SimulationDescriptorReference("TraitorBonusThresholdDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //FactionTrait.xsd
        references.add(new SimulationDescriptorReference("FactionTrait.xsd", "SimulationDescriptorReference", "Name"));
        //ElectionActionDefinition.xsd
        references.add(new SimulationDescriptorReference("ElectionActionDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //EmpireImprovementDefinition.xsd
        references.add(new SimulationDescriptorReference("EmpireImprovementDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //FactionAffinity.xsd
        references.add(new SimulationDescriptorReference("FactionAffinity.xsd", "SimulationDescriptorReference", "Name"));
        //Faction.xsd
        references.add(new SimulationDescriptorReference("Faction.xsd", "SimulationDescriptorReference", "Name"));
        //MinorFaction.xsd
        references.add(new SimulationDescriptorReference("MinorFaction.xsd", "SimulationDescriptorReference", "Name"));
        //MinorStateDefinition.xsd
        references.add(new SimulationDescriptorReference("MinorStateDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //MarketplaceEventDefinition.xsd
        references.add(new SimulationDescriptorReference("MarketplaceEventDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //ManpowerUpgradeDefinition.xsd
        references.add(new SimulationDescriptorReference("ManpowerUpgradeDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //MajorFaction.xsd
        references.add(new SimulationDescriptorReference("MajorFaction.xsd", "SimulationDescriptorReference", "Name"));
        //LevelUpRuleDefinition.xsd
        references.add(new SimulationDescriptorReference("LevelUpRuleDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //GovernmentDefinition.xsd
        references.add(new SimulationDescriptorReference("GovernmentDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //ForeignAffairsConstructibleElement.xsd
        references.add(new SimulationDescriptorReference("ForeignAffairsConstructibleElement.xsd", "SimulationDescriptorReference", "Name"));
        //FactionAffinityMapping.xsd
        references.add(new SimulationDescriptorReference("FactionAffinityMapping.xsd", "SimulationDescriptorReference", "Name"));
        //HappinessStatusSetDefinition.xsd
        references.add(new SimulationDescriptorReference("HappinessStatusSetDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //GroundBattleStrategyDefinition.xsd
        references.add(new SimulationDescriptorReference("GroundBattleStrategyDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HeroDefinition.xsd
        references.add(new SimulationDescriptorReference("HeroDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HeroClassDefinition.xsd
        references.add(new SimulationDescriptorReference("HeroClassDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HeroAffinityDefinition.xsd
        references.add(new SimulationDescriptorReference("HeroAffinityDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HereticRelationStateDefinition.xsd
        references.add(new SimulationDescriptorReference("HereticRelationStateDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HackingProgramDefinition.xsd
        references.add(new SimulationDescriptorReference("HackingProgramDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HeroUnlockGaugeDefinition.xsd
        references.add(new SimulationDescriptorReference("HeroUnlockGaugeDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HeroPoliticsDefinition.xsd
        references.add(new SimulationDescriptorReference("HeroPoliticsDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //TradableDefinition.xsd
        references.add(new SimulationDescriptorReference("TradableDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //TradableCategoryDefinition.xsd
        references.add(new SimulationDescriptorReference("TradableCategoryDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //PlanetReserveDefinition.xsd
        references.add(new SimulationDescriptorReference("PlanetReserveDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //PopulationAspirationDefinition.xsd
        references.add(new SimulationDescriptorReference("PopulationAspirationDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //PoliticsSupportStatusDefinition.xsd
        references.add(new SimulationDescriptorReference("PoliticsSupportStatusDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //PoliticsDefinition.xsd
        references.add(new SimulationDescriptorReference("PoliticsDefinition.xsd", "MainPoliticsDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("PoliticsDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //PlanetCuriosityExpeditionDefinition.xsd
        references.add(new SimulationDescriptorReference("PlanetCuriosityExpeditionDefinition.xsd", "InProgressSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("PlanetCuriosityExpeditionDefinition.xsd", "QueuedSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("PlanetCuriosityExpeditionDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //RankDefinition.xsd
        references.add(new SimulationDescriptorReference("RankDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //ResourceDepositDefinition.xsd
        references.add(new SimulationDescriptorReference("ResourceDepositDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //ResourceDefinition.xsd
        references.add(new SimulationDescriptorReference("ResourceDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //RecipeSpecialEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("RecipeSpecialEffectDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //RecipeSlotDefinition.xsd
        references.add(new SimulationDescriptorReference("RecipeSlotDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //RecipeIngredientDefinition.xsd
        references.add(new SimulationDescriptorReference("RecipeIngredientDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //QuestEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("QuestEffectDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //PopulationTrait.xsd
        references.add(new SimulationDescriptorReference("PopulationTrait.xsd", "SimulationDescriptorReference", "Name"));
        //SpecialNodeDefinition.xsd
        references.add(new SimulationDescriptorReference("PopulationTrait.xsd", "EncounterGlobalEffectDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("PopulationTrait.xsd", "DockedFleetsSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("PopulationTrait.xsd", "SimulationDescriptorReference", "Name"));
        //ShipRoleDefinition.xsd
        references.add(new SimulationDescriptorReference("ShipRoleDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //ShipDesignDefinition.xsd
        references.add(new SimulationDescriptorReference("ShipDesignDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //TradingCompanyImprovementDefinition.xsd
        references.add(new SimulationDescriptorReference("TradingCompanyImprovementDefinition.xsd", "InProgressSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("TradingCompanyImprovementDefinition.xsd", "QueuedSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("TradingCompanyImprovementDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //TradingCompanyDefinition.xsd
        references.add(new SimulationDescriptorReference("TradingCompanyDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //TradableSubCategoryDefinition.xsd
        references.add(new SimulationDescriptorReference("TradableSubCategoryDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //TimeBubbleDefinition.xsd
        references.add(new SimulationDescriptorReference("TimeBubbleDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //TemporaryEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("TemporaryEffectDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //TechnologyStageDefinition.xsd
        references.add(new SimulationDescriptorReference("TechnologyStageDefinition.xsd", "InProgressSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("TechnologyStageDefinition.xsd", "QueuedSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("TechnologyStageDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //TechnologyLinkDefinition.xsd
        references.add(new SimulationDescriptorReference("TechnologyLinkDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //StarSystemNodeDefinition.xsd
        references.add(new SimulationDescriptorReference("StarSystemNodeDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //SquadronStanceDefinition.xsd
        references.add(new SimulationDescriptorReference("SquadronStanceDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //SalvoDefinition.xsd
        references.add(new SimulationDescriptorReference("SalvoDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //PopulationPoliticalTraitDefinition.xsd
        references.add(new SimulationDescriptorReference("PopulationPoliticalTraitDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //PopulationCollectionBonusTrait.xsd
        references.add(new SimulationDescriptorReference("PopulationCollectionBonusTrait.xsd", "SimulationDescriptorReference", "Name"));
        //PlanetColonizationConstructibleElement.xsd
        references.add(new SimulationDescriptorReference("PlanetColonizationConstructibleElement.xsd", "InProgressSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("PlanetColonizationConstructibleElement.xsd", "QueuedSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("PlanetColonizationConstructibleElement.xsd", "SimulationDescriptorReference", "Name"));
        //PiratePowerLevelDefinition.xsd
        references.add(new SimulationDescriptorReference("PiratePowerLevelDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HonorThresholdSetDefinition.xsd
        references.add(new SimulationDescriptorReference("HonorThresholdSetDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HullDefinition.xsd
        references.add(new SimulationDescriptorReference("HullDefinition.xsd", "InProgressSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("HullDefinition.xsd", "QueuedSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("HullDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //HonorThresholdSetDefinition.xsd
        references.add(new SimulationDescriptorReference("GroundBattleOutcomeDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //EntityActionDefinition.xsd
        references.add(new SimulationDescriptorReference("EntityActionDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //EncounterFormationDefinition.xsd
        references.add(new SimulationDescriptorReference("EncounterFormationDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //NodeEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("NodeEffectDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //ScienceConstructibleElement.xsd
        references.add(new SimulationDescriptorReference("ScienceConstructibleElement.xsd", "InProgressSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("ScienceConstructibleElement.xsd", "QueuedSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("ScienceConstructibleElement.xsd", "SimulationDescriptorReference", "Name"));
        //ModuleDefinition.xsd
        references.add(new SimulationDescriptorReference("ModuleDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //LawDefinition.xsd
        references.add(new SimulationDescriptorReference("LawDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //IndustryConstructibleElement.xsd
        references.add(new SimulationDescriptorReference("IndustryConstructibleElement.xsd", "InProgressSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("IndustryConstructibleElement.xsd", "QueuedSimulationDescriptorReference", "Name"));
        references.add(new SimulationDescriptorReference("IndustryConstructibleElement.xsd", "SimulationDescriptorReference", "Name"));
        //EncounterEntityDefinition.xsd
        references.add(new SimulationDescriptorReference("EncounterEntityDefinition.xsd", "SimulationDescriptorReference", "Name"));
        //DiplomaticPressureEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("EncounterEntityDefinition.xsd", "SimulationDescriptorReferenceOnInitiator", "Name"));
        references.add(new SimulationDescriptorReference("EncounterEntityDefinition.xsd", "SimulationDescriptorReferenceOnTarget", "Name"));
        references.add(new SimulationDescriptorReference("EncounterEntityDefinition.xsd", "SystemDescriptor", "Name"));
        references.add(new SimulationDescriptorReference("EncounterEntityDefinition.xsd", "FleetDescriptor", "Name"));
        //ShipConditionalConstructionEffectDefinition.xsd
        references.add(new SimulationDescriptorReference("ShipConditionalConstructionEffectDefinition.xsd", "SimulationDescriptorReference", "Name"));
        return references;
    }
}
