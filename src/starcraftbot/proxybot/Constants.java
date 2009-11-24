package starcraftbot.proxybot;
/**
 * Contains constant and enumerations values for:
 *  - races
 * 	- unit types
 *  - tech types
 *  - upgrade types
 *  - orders
 *  
 *  To convert back to starcraft names:
 *   2. replace "___" with "-" (for U-238 shells)
 *   2. replace "__" with "'"  (for Queen's next)
 *   3. replace "_" with " "
 */
public final class Constants {
	
	public static final int NumUnitTypes = 230;
	public static final int NumTechTypes = 47;
	public static final int NumUpgradeTypes = 63;
	
	// Races
	public static final int Zerg = 0;
    public static final int Terran = 1;
    public static final int Protoss = 2;
    public static final int Random = 3;
    public static final int Other = 4;
    public static final int Race_None = 5;
    public static final int Race_Unknown = 6;	
	
	// unit types
	public static final int Terran_Marine = 0;
	public static final int Terran_Ghost = 1;
	public static final int Terran_Vulture = 2;
	public static final int Terran_Goliath = 3;
	public static final int Terran_Siege_Tank_Tank_Mode = 5;
	public static final int Terran_SCV = 7;
	public static final int Terran_Wraith = 8;
	public static final int Terran_Science_Vessel = 9;
	public static final int Terran_Dropship = 11;
	public static final int Terran_Battlecruiser = 12;
	public static final int Terran_Vulture_Spider_Mine = 13;
	public static final int Terran_Nuclear_Missile = 14;
	public static final int Terran_Siege_Tank_Siege_Mode = 30;
	public static final int Terran_Medic = 34;
	public static final int Zerg_Larva = 35;
	public static final int Terran_Firebat = 32;
	public static final int Spell_Scanner_Sweep = 33;
	public static final int Zerg_Hydralisk = 38;
	public static final int Zerg_Ultralisk = 39;
	public static final int Zerg_Egg = 36;
	public static final int Zerg_Zergling = 37;
	public static final int Zerg_Overlord = 42;
	public static final int Zerg_Mutalisk = 43;
	public static final int Zerg_Broodling = 40;
	public static final int Zerg_Drone = 41;
	public static final int Zerg_Defiler = 46;
	public static final int Zerg_Scourge = 47;
	public static final int Zerg_Guardian = 44;
	public static final int Zerg_Queen = 45;
	public static final int Zerg_Infested_Terran = 50;
	public static final int Zerg_Cocoon = 59;
	public static final int Terran_Valkyrie = 58;
	public static final int Protoss_Dark_Archon = 63;
	public static final int Zerg_Devourer = 62;
	public static final int Protoss_Dark_Templar = 61;
	public static final int Protoss_Corsair = 60;
	public static final int Protoss_Archon = 68;
	public static final int Protoss_Shuttle = 69;
	public static final int Protoss_Scout = 70;
	public static final int Protoss_Arbiter = 71;
	public static final int Protoss_Probe = 64;
	public static final int Protoss_Zealot = 65;
	public static final int Protoss_Dragoon = 66;
	public static final int Protoss_High_Templar = 67;
	public static final int Protoss_Carrier = 72;
	public static final int Protoss_Interceptor = 73;
	public static final int Protoss_Scarab = 85;
	public static final int Protoss_Observer = 84;
	public static final int Protoss_Reaver = 83;
	public static final int Critter_Scantid = 93;
	public static final int Critter_Ragnasaur = 95;
	public static final int Critter_Kakaru = 94;
	public static final int Critter_Rhynadon = 89;
	public static final int Critter_Bengalaas = 90;
	public static final int Zerg_Lurker = 103;
	public static final int Critter_Ursadon = 96;
	public static final int Zerg_Lurker_Egg = 97;
	public static final int Terran_Refinery = 110;
	public static final int Terran_Barracks = 111;
	public static final int Terran_Nuclear_Silo = 108;
	public static final int Terran_Supply_Depot = 109;
	public static final int Terran_Command_Center = 106;
	public static final int Terran_Comsat_Station = 107;
	public static final int Spell_Disruption_Web = 105;
	public static final int Terran_Physics_Lab = 118;
	public static final int Terran_Covert_Ops = 117;
	public static final int Terran_Science_Facility = 116;
	public static final int Terran_Control_Tower = 115;
	public static final int Terran_Starport = 114;
	public static final int Terran_Factory = 113;
	public static final int Terran_Academy = 112;
	public static final int Special_Ion_Cannon = 127;
	public static final int Special_Crashed_Norad_II = 126;
	public static final int Terran_Bunker = 125;
	public static final int Terran_Missile_Turret = 124;
	public static final int Terran_Armory = 123;
	public static final int Terran_Engineering_Bay = 122;
	public static final int Terran_Machine_Shop = 120;
	public static final int Zerg_Greater_Spire = 137;
	public static final int Zerg_Defiler_Mound = 136;
	public static final int Zerg_Evolution_Chamber = 139;
	public static final int Zerg_Queen__s_Nest = 138;
	public static final int Zerg_Spire = 141;
	public static final int Zerg_Ultralisk_Cavern = 140;
	public static final int Zerg_Creep_Colony = 143;
	public static final int Zerg_Spawning_Pool = 142;
	public static final int Zerg_Hatchery = 131;
	public static final int Zerg_Infested_Command_Center = 130;
	public static final int Zerg_Hive = 133;
	public static final int Zerg_Lair = 132;
	public static final int Zerg_Hydralisk_Den = 135;
	public static final int Zerg_Nydus_Canal = 134;
	public static final int Special_Cerebrate_Daggoth = 152;
	public static final int Protoss_Nexus = 154;
	public static final int Protoss_Robotics_Facility = 155;
	public static final int Protoss_Pylon = 156;
	public static final int Protoss_Assimilator = 157;
	public static final int Protoss_Observatory = 159;
	public static final int Zerg_Spore_Colony = 144;
	public static final int Zerg_Sunken_Colony = 146;
	public static final int Special_Overmind_With_Shell = 147;
	public static final int Special_Overmind = 148;
	public static final int Zerg_Extractor = 149;
	public static final int Special_Mature_Chrysalis = 150;
	public static final int Special_Cerebrate = 151;
	public static final int Protoss_Robotics_Support_Bay = 171;
	public static final int Protoss_Arbiter_Tribunal = 170;
	public static final int Protoss_Fleet_Beacon = 169;
	public static final int Special_Stasis_Cell_Prison = 168;
	public static final int Special_XelNaga_Temple = 175;
	public static final int Special_Protoss_Temple = 174;
	public static final int Special_Khaydarin_Crystal_Form = 173;
	public static final int Protoss_Shield_Battery = 172;
	public static final int Protoss_Citadel_of_Adun = 163;
	public static final int Protoss_Photon_Cannon = 162;
	public static final int Protoss_Gateway = 160;
	public static final int Protoss_Stargate = 167;
	public static final int Protoss_Forge = 166;
	public static final int Protoss_Templar_Archives = 165;
	public static final int Protoss_Cybernetics_Core = 164;
	public static final int Special_Psi_Disrupter = 190;
	public static final int Resource_Vespene_Geyser = 188;
	public static final int Special_Warp_Gate = 189;
	public static final int Resource_Mineral_Field = 176;
	public static final int Special_Overmind_Cocoon = 201;
	public static final int Special_Power_Generator = 200;
	public static final int Spell_Dark_Swarm = 202;
	public static final int Type_Unknown = 229;
	public static final int Type_None = 228;	

	// tech types
	public static final int Stim_Packs = 0;
	public static final int Lockdown = 1;
	public static final int EMP_Shockwave = 2;
	public static final int Spider_Mines = 3;
	public static final int Scanner_Sweep = 4;
	public static final int Tank_Siege_Mode = 5;
	public static final int Defensive_Matrix = 6;
	public static final int Irradiate = 7;
	public static final int Yamato_Gun = 8;
	public static final int Cloaking_Field = 9;
	public static final int Personnel_Cloaking = 10;
	public static final int Burrowing = 11;
	public static final int Infestation = 12;
	public static final int Spawn_Broodlings = 13;
	public static final int Dark_Swarm = 14;
	public static final int Plague = 15;
	public static final int Consume = 16;
	public static final int Ensnare = 17;
	public static final int Parasite = 18;
	public static final int Psionic_Storm = 19;
	public static final int Hallucination = 20;
	public static final int Recall = 21;
	public static final int Stasis_Field = 22;
	public static final int Archon_Warp = 23;
	public static final int Restoration = 24;
	public static final int Disruption_Web = 25;
	public static final int Mind_Control = 27;
	public static final int Dark_Archon_Meld = 28;
	public static final int Feedback = 29;
	public static final int Optical_Flare = 30;
	public static final int Maelstrom = 31;
	public static final int Lurker_Aspect = 32;
	public static final int Healing = 34;
	public static final int Tech_None = 44;
	public static final int Tech_Unknown = 45;
	public static final int Nuclear_Strike = 46;	

	// upgrade types
	public static final int Terran_Infantry_Armor = 0;
	public static final int Terran_Vehicle_Plating = 1;
	public static final int Terran_Ship_Plating = 2;
	public static final int Zerg_Carapace = 3;
	public static final int Zerg_Flyer_Carapace = 4;
	public static final int Protoss_Armor = 5;
	public static final int Protoss_Plating = 6;
	public static final int Terran_Infantry_Weapons = 7;
	public static final int Terran_Vehicle_Weapons = 8;
	public static final int Terran_Ship_Weapons = 9;
	public static final int Zerg_Melee_Attacks = 10;
	public static final int Zerg_Missile_Attacks = 11;
	public static final int Zerg_Flyer_Attacks = 12;
	public static final int Protoss_Ground_Weapons = 13;
	public static final int Protoss_Air_Weapons = 14;
	public static final int Protoss_Plasma_Shields = 15;
	public static final int U___238_Shells = 16;
	public static final int Ion_Thrusters = 17;
	public static final int Titan_Reactor = 19;
	public static final int Ocular_Implants = 20;
	public static final int Moebius_Reactor = 21;
	public static final int Apollo_Reactor = 22;
	public static final int Colossus_Reactor = 23;
	public static final int Ventral_Sacs = 24;
	public static final int Antennae = 25;
	public static final int Pneumatized_Carapace = 26;
	public static final int Metabolic_Boost = 27;
	public static final int Adrenal_Glands = 28;
	public static final int Muscular_Augments = 29;
	public static final int Grooved_Spines = 30;
	public static final int Gamete_Meiosis = 31;
	public static final int Metasynaptic_Node = 32;
	public static final int Singularity_Charge = 33;
	public static final int Leg_Enhancements = 34;
	public static final int Scarab_Damage = 35;
	public static final int Reaver_Capacity = 36;
	public static final int Gravitic_Drive = 37;
	public static final int Sensor_Array = 38;
	public static final int Gravitic_Boosters = 39;
	public static final int Khaydarin_Amulet = 40;
	public static final int Apial_Sensors = 41;
	public static final int Gravitic_Thrusters = 42;
	public static final int Carrier_Capacity = 43;
	public static final int Khaydarin_Core = 44;
	public static final int Argus_Jewel = 47;
	public static final int Argus_Talisman = 49;
	public static final int Caduceus_Reactor = 51;
	public static final int Chitinous_Plating = 52;
	public static final int Anabolic_Synthesis = 53;
	public static final int Charon_Booster = 54;
	public static final int Upgrade_None = 61;
	public static final int Upgrade_Unknown = 62;

	/** order commands */
	public enum Order {	
		Die,
	    Stop,
	    Guard,
	    PlayerGuard,
	    TurretGuard,
	    BunkerGuard,
	    Move,
	    ReaverStop,
	    Attack1,
	    Attack2,
	    AttackUnit,
	    AttackFixedRange,
	    AttackTile,
	    Hover,
	    AttackMove,
	    InfestMine1,
	    Nothing1,
	    Powerup1,
	    TowerGuard,
	    TowerAttack,
	    VultureMine,
	    StayinRange,
	    TurretAttack,
	    Nothing2,
	    Nothing3,
	    DroneStartBuild,
	    DroneBuild,
	    InfestMine2,
	    InfestMine3,
	    InfestMine4,
	    BuildTerran,
	    BuildProtoss1,
	    BuildProtoss2,
	    ConstructingBuilding,
	    Repair1,
	    Repair2,
	    PlaceAddon,
	    BuildAddon,
	    Train,
	    RallyPoint1,
	    RallyPoint2,
	    ZergBirth,
	    Morph1,
	    Morph2,
	    BuildSelf1,
	    ZergBuildSelf,
	    Build5,
	    Enternyduscanal,
	    BuildSelf2,
	    Follow,
	    Carrier,
	    CarrierIgnore1,
	    CarrierStop,
	    CarrierAttack1,
	    CarrierAttack2,
	    CarrierIgnore2,
	    CarrierFight,
	    HoldPosition1,
	    Reaver,
	    ReaverAttack1,
	    ReaverAttack2,
	    ReaverFight,
	    ReaverHold,
	    TrainFighter,
	    StrafeUnit1,
	    StrafeUnit2,
	    RechargeShields1,
	    Rechargeshields2,
	    ShieldBattery,
	    Return,
	    DroneLand,
	    BuildingLand,
	    BuildingLiftoff,
	    DroneLiftoff,
	    Liftoff,
	    ResearchTech,
	    Upgrade,
	    Larva,
	    SpawningLarva,
	    Harvest1,
	    Harvest2,
	    MoveToGas, // Unit is moving to refinery
	    WaitForGas, // Unit is waiting to enter the refinery (another unit is currently in it)
	    HarvestGas, // Unit is in refinery
	    ReturnGas, // Unit is returning gas to center
	    MoveToMinerals, // Unit is moving to mineral patch
	    WaitForMinerals, // Unit is waiting to use the mineral patch (another unit is currently mining from it)
	    MiningMinerals, // Unit is mining minerals from mineral patch
	    Harvest3,
	    Harvest4,
	    ReturnMinerals, // Unit is returning minerals to center
	    Harvest5,
	    EnterTransport,
	    Pickup1,
	    Pickup2,
	    Pickup3,
	    Pickup4,
	    Powerup2,
	    SiegeMode,
	    TankMode,
	    WatchTarget,
	    InitCreepGrowth,
	    SpreadCreep,
	    StoppingCreepGrowth,
	    GuardianAspect,
	    WarpingArchon,
	    CompletingArchonsummon,
	    HoldPosition2,
	    HoldPosition3,
	    Cloak,
	    Decloak,
	    Unload,
	    MoveUnload,
	    FireYamatoGun1,
	    FireYamatoGun2,
	    MagnaPulse,
	    Burrow,
	    Burrowed,
	    Unburrow,
	    DarkSwarm,
	    CastParasite,
	    SummonBroodlings,
	    EmpShockwave,
	    NukeWait,
	    NukeTrain,
	    NukeLaunch,
	    NukePaint,
	    NukeUnit,
	    NukeGround,
	    NukeTrack,
	    InitArbiter,
	    CloakNearbyUnits,
	    PlaceMine,
	    Rightclickaction,
	    SapUnit,
	    SapLocation,
	    HoldPosition4,
	    Teleport,
	    TeleporttoLocation,
	    PlaceScanner,
	    Scanner,
	    DefensiveMatrix,
	    PsiStorm,
	    Irradiate,
	    Plague,
	    Consume,
	    Ensnare,
	    StasisField,
	    Hallucianation1,
	    Hallucination2,
	    ResetCollision1,
	    ResetCollision2,
	    Patrol,
	    CTFCOPInit,
	    CTFCOP1,
	    CTFCOP2,
	    ComputerAI,
	    AtkMoveEP,
	    HarassMove,
	    AIPatrol,
	    GuardPost,
	    RescuePassive,
	    Neutral,
	    ComputerReturn,
	    InitPsiProvider,
	    SelfDestrucing,
	    Critter,
	    HiddenGun,
	    OpenDoor,
	    CloseDoor,
	    HideTrap,
	    RevealTrap,
	    Enabledoodad,
	    Disabledoodad,
	    Warpin,
	    Medic,
	    MedicHeal1,
	    HealMove,
	    MedicHoldPosition,
	    MedicHeal2,
	    Restoration,
	    CastDisruptionWeb,
	    CastMindControl,
	    WarpingDarkArchon,
	    CastFeedback,
	    CastOpticalFlare,
	    CastMaelstrom,
	    JunkYardDog,
	    Fatal,
	    None,
	    Unknown 
	}
}
