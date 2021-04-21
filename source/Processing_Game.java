import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Processing_Game extends PApplet {



  int x;
  int y;
  int click = 0;
  PImage arrow, death, default1, defaultText, diamond, emerald, gold, helpScreen, house, iron, onyx, quartz, ravine, river, rock, ruby, titleScreen, well, rest, goose;
  SoundFile bgMusic, deposit, gemFind, gooseHonk, houseChime, miningSound, sleepMusic, puppyBark, deathMusic, rain, riverSound, wellExplore;
  Miner miner = new Miner();
  int random;
  double rando;

public void setup(){
  
  
  arrow = loadImage("Arrow.png");
  death = loadImage("Death.png");
  default1 = loadImage("Default_1.png");
  defaultText = loadImage("Default Text Box.png");
  diamond = loadImage("Diamond_1-100.jpg");
  emerald = loadImage("Emerald_1-100.jpg");
  gold = loadImage("Gold_1-100.jpg");
  helpScreen = loadImage("Help Screen.png");
  house = loadImage("House Text Box.png");
  iron = loadImage("Iron-100.jpg");
  onyx = loadImage("Onyx.jpg");
  quartz = loadImage("Quartz-100.jpg");
  ravine = loadImage("Ravine Text Box.png");
  river = loadImage("River Text Box.png");
  rock = loadImage("Rock Text Box.png");
  ruby = loadImage("Ruby.jpg");
  titleScreen = loadImage("Title Screen.png");
  well = loadImage("Well Text Box.png");
  rest = loadImage("Rest.png");
  goose = loadImage("Goose.png");
  bgMusic = new SoundFile(this, "bgMusic.wav");
  deposit = new SoundFile(this, "deposit.wav");
  gemFind = new SoundFile(this, "gemFind.wav"); 
  gooseHonk = new SoundFile(this, "gooseHonk.wav"); 
  houseChime = new SoundFile(this, "house.wav"); 
  miningSound = new SoundFile(this, "miningSound.wav"); 
  sleepMusic = new SoundFile(this, "Nidra_in_the_Sky_with_Ayler.wav"); 
  puppyBark = new SoundFile(this, "puppyBark.wav");
  deathMusic = new SoundFile(this, "Quite_Night.wav"); 
  rain = new SoundFile(this, "rain.wav"); 
  riverSound = new SoundFile(this, "river.wav"); 
  wellExplore = new SoundFile(this, "wellExplore.wav");
  textSize(26);
  textAlign(CENTER, CENTER);
  
}//end setup

public void draw(){

  if(click == 0) {
    background(titleScreen);
    
    if(mouseX >= 1086 && mouseX <= 1449 && mouseY >= 692 && mouseY <= 822 && mousePressed) {
    click = 1;
    }
    
    else if(mouseX >= 500 && mouseX <= 863 && mouseY >= 695 && mouseY <= 825 && mousePressed) {
    click = 2;
    }
  
  }//end click 0 if
  
  
  else if (click == 1) {
     background(helpScreen);
     noFill();
     rect(1888, 13, 20, 27);
     
     if(mouseX >= 1888 && mouseX <= 1908 && mouseY >= 13 && mouseY <= 39 && mousePressed) {
       click = 0;
     }
       
  }//end click 1 if
    
  else if(click == 2) {
    
    background(default1); 
    
    if(y >= 910 && y <= 1000) {
     
      if(x >= 95 && x <= 335) {
        miner.Move();
      }
      
      else if(x >= 393 && x <= 633) {
        miner.Mine();
      }
      
      else if(x >= 687  && x <= 927) {
        miner.Rest();
      }
      
      else if(x >= 982 && x <= 1222) {
        miner.inventoryContents();
      }
      
      else if(x >= 1278 && x <= 1518) {
        miner.Bank();
      }
      
      else if(x >= 1580 && x <= 1820) {
        miner.Info();
      }
      
    }//end if
    
    else if(x >= 1857 && x <= 1925  && y >= 13 && y <= 55) {
        background(helpScreen); 
        noFill();
        rect(1888, 13, 20, 27);
        noLoop();
       
       if(mouseX >= 1888 && mouseX <= 1908 && mouseY >= 13 && mouseY <= 39 && mousePressed) {
         background(default1);
        
       }
    }
    
  }//end click 2 if 
  
}//end draw

public void mousePressed() {
  
  x = mouseX;
  y = mouseY;
  random = (int)(Math.random() * 50) + 1;
  rando = (double)(Math.random());
  loop();
  
}//end mousePressed


class Miner {
  
  private String name;
  private int xValue;
  private Gem[] bankAccount = new Gem[1000];
  private boolean alive;
  private Gem[] inventory;
  private final int INVENTORY_SLOTS = 45;
  private int ba = 0;
  private int i = 0;
  private boolean gemHere;

  public Miner() {
    
    name = "Stephen the Goldfish";
    xValue = 0;
    alive = true;
    inventory = new Gem[INVENTORY_SLOTS];
    
    for(int j = 0; j > INVENTORY_SLOTS; j++) {
      inventory[j] = null;
    }//end for
    
  }//end empty Miner
  
  public void Move() {
 
    String str = "";

    if(alive == true) {

      switch (random) {
        
        default: { 
          background(defaultText);
          str = "There is nothing here";
          xValue++;
          noLoop();
          break;
        }//end default case
          
        case 26: case 27: case 28: case 29: case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39: case 40: {
          background(rock);
          gemHere = true;
          str =  name + " runs into a rock. Will they mine it? If yes, use the mine command. If no, feel free to move on. ";
          xValue++;
          noLoop();
          break;
        }//end gem case
        
        
        case 41: case 42: case 43: case 44: case 45: {
          background(well);
          text("As they walk " + name + " runs into a well. It is old and moss covered and seems to have dried up. It must have been here for a while. It could lead to treasures. Will they go down it to explore? Press 'y' to explore or 'n' to move on.", 190, 681, 1550, 200);
          
          if(keyPressed) { 
            
            if(key == 'y') {
            
              if(rando >= 0.9f)  { 
                background(well);
                
                if(!isInventoryFull()) {
                  Gem gem = new Gem('R');
                  inventory[i] = gem;
                  i++;
                  str = name + " braved the darkness and explored the small tunnel system below the well. As they walked they saw a gilmmer in the light of their torch. " + name + " found a Ruby! A Ruby had been added to " + name + "'s inventory";
                  xValue++;
                  noLoop();
                }
                
                else if(isInventoryFull()) {
                  str = name + " braved the darkness and explored the small tunnel system below the well. As they walked they saw a gilmmer in the light of their torch. " + name + " found a Ruby but their inventory is full :(";
                  xValue++;
                  noLoop();                
                }
                
              }//end >=0.9
              
              else if(rando == 0.8f) {
                background(well);
                image(death, 31, 30);
                alive = false;
                str =  "As " + name + " explored the well, they found a series of passages that were almost maze like. They went deeper and deeper into the tunnels and that is the last we know of them. " + name + " has died.";
                name = "Ghost of " + name;
                xValue++;
                noLoop();
              }//end 0.8
              
                else if(rando <= 0.7f) {
                  background(well);
                  str =  name + " explored but found nothing. Better luck next time.";
                  xValue++;
                  noLoop();
                }//end <= 0.7
                
             }//end y
          
            else if(key == 'n') {
              background(well);
              str = name + " leaves the well be.";
              xValue++;
              noLoop();
            }//end n
          
          }//end keyPressed
       
          break;
          
        } //end well case 
       
        
        case 46: case 47: {
          background(river);
          text(name + " comes across a river. It is running swiftly and the water looks very clear. They can see some fishes swimming along the current. If they will attempt to cross it press 'y'. If not press 'n'.", 190, 681, 1550, 200); 
          
          if(keyPressed) {
            
            if(key == 'y') {
              
              if(rando >= 0.9f)  { 
                background(river);
                
                if(!isInventoryFull()) {
                  Gem gem = new Gem('E');
                  inventory[i] = gem;
                  i++;
                  str =  "While crossing the river " + name + " spotted something glimmering in the water. " + name + " found an Emerald! An Emerald had been added to " + name + "'s inventory.";
                  xValue++;
                  noLoop();
                }
                
                else if(isInventoryFull()) {
                  str =  "While crossing the river " + name + " spotted something glimmering in the water. " + name + " found an Emerald but their inventory is full :(";
                  xValue++;
                  noLoop();
                }
                
              }//end >= 0.9
              
              else if(rando == 0.8f) {
                background(river);
                image(death, 31, 30);
                alive = false;
                str =  "As " + name + " attempted to cross the river they discovered that the current was much to swift to swim through. Tragically " + name + " has died.";
                name = "Ghost of " + name;
                xValue++;
                noLoop();
              }//end 0.8
                
              else if(rando <= 0.7f) {
                background(river);
                str =  name + " crossed safely and with ease. A fish even swam by them to say hello!";
                xValue++;
                noLoop();
              }//end <= 0.7 
                
          }//end y
          
          else if(key == 'n') {
            background(river);
            str =  name + " decided that crossing a river with that swift of a current would not be a good idea. They decide to try to find a bridge instead.";
            xValue++;
            noLoop();
          }//end n
          
        }//end keyPressed
        
        break;
        
        }//end river case
        
        
        case 48: case 49: {
           background(house);
           text(name + " comes across a house. It looks very quaint and in good repair. The lawn is neatly cut and there are many beautiful flowers in the small plots on either side of the front door. You can smell fresh baked cookies wafting from the open windows and hear some pleasant music. Does " + name + " knock on the door? Press 'y' if they will or 'n' if they will not.", 190, 681, 1550, 200); 
          
          if(keyPressed) {
          
            if(key == 'y') {
              
              if(rando >= 0.9f)  { 
                background(house);
                
                if(!isInventoryFull()) {
                  Gem gem = new Gem('D');
                  inventory[i] = gem;
                  i++;
                  str = name + " knocks on the door but there is not answer. As they turn to leave they see something shining from the main path. It's a Diamond! A Diamond had been added to " + name + "'s inventory.";
                 xValue++;
                  noLoop();
                }
                
                else if(isInventoryFull()) {
                  str = name + " knocks on the door but there is not answer. As they turn to leave they see something shining from the main path. It's a Diamond but " + name + "'s inventory is full :(";
                  xValue++;
                  noLoop();
                }
                
              }//end 0.9
              
              else if(rando == 0.8f) {
                background(house);
                image(death, 31, 30);
                alive = false;
                str =  "Yeah, talking to strangers usually isn't the best idea possible and in this case it was the worst idea possible. " + name + " has died under mysterious circumstances.";
                name = "Ghost of " + name;
                xValue++;
                noLoop();
              }//end 0.8
                
              else if(rando <= 0.7f) {
                background(house);
                str = name + " knocks on the door and was greeted by a kindly old woman. As they sit down to eat some fresh chocolate chip cookies " + name + " tells the old woman about their adventures so far. The two talk for hours until the cookies are almost gone. She thanks them for coming to visit as most people are too scared to knock on her door and it was getting awfully lonely. She sends " + name + " off with cookies and they promise to return soon to chat. What a lovely afternoon!";
                xValue++;
                noLoop();
                
              }//end <=0.7
            
            }//end y
            
            else if(key == 'n'){
              background(house);
              str = name + " decided that whoever owns the house probably lives out here so they won't be bothered by random people. " + name + " leaves.";
              xValue++;
              noLoop();
            }//end n
          
          }//end keyPressed
          
          break;
          
        }//end house case
     
        
        case 50: {
           background(ravine);
           text(name + " comes accross a ravine. It seems to be quite deep as " + name + " cannot see the bottom. Will they attempt to cross it? Press 'y' if yes or 'n' if no.", 190, 681, 1550, 200); 
            
           if(keyPressed) {
             
            if(key == 'y') {
              
              if(rando == 0.6f)  { 
                background(ravine);
                
                if(!isInventoryFull()) {
                  Gem gem = new Gem('O');
                  inventory[i] = gem;
                  i++;
                  str = "While crossing the ravine " + name + " spotted something reflecting the sunlight on one of the ravine's ledges. " + name + " found an Onyx! An Onyx had been added to " + name + "'s inventory.";
                  xValue++;
                  noLoop();
                }
                  
                else if(isInventoryFull()) {
                  str = "While crossing the ravine " + name + " spotted something reflecting the sunlight on one of the ravine's ledges. " + name + " found an Onyx but their inventory is full :(";
                  xValue++;
                  noLoop(); 
                }
                
              }// 6.0
              
              else if(rando >= 0.7f) {
                background(ravine);
                image(death, 31, 30);
                alive = false;
                str =  "As " + name + " attempted to cross the ravine.  " + name + " fell.";
                name = "Ghost of " + name;
                xValue++;
                noLoop();
              }//end >=0.7
                
              else if(rando <= 0.5f) {
                background(ravine);
                str = name + " crossed the ravine with almost no trouble at all.";
                xValue++;
                noLoop();
              }//end <=5.0
              
            }//end y
            
            else if(key == 'n') {
              background(ravine);
              str = name + " decided that crossing this ravine would not be a good idea. They decide to find a way around.";
              xValue++;
              noLoop();
            }//end n
            
          }//end keyPressed
         
           break;

         }//end ravine case
         
      }//end switch

    }//end alive if
    
    else if(alive == false) {
      background(defaultText);
      str =  "Sadly " + name + " cannot adventure like they used to in life. Restart to try again.";
    }
    
    text(str, 190, 681, 1550, 200);
    
  }//end Move
  
  
  public void Mine() {
    
    String str = "";
    Gem gem;
    
    if(alive == true) {
      if(gemHere == true) {
        if(!isInventoryFull()) {
          background(defaultText);
          gem = new Gem();
          inventory[i] = gem;
          
 String pic = gem.getGemType();
 pic.toLowerCase();
 
 if(pic == "iron")
     image(iron, 800, 500);
 if(pic == "gold")
     image(gold, 800, 500);
 if(pic == "ruby")
     image(ruby, 800, 500);
 if(pic == "diamond")
     image(diamond, 800, 500);
 if(pic == "emerald")
     image(emerald, 800, 500);
 if(pic == "quartz")
     image(quartz, 800, 500);
 if(pic == "onyx")     
     image(onyx, 800, 500);
          
          
          
          i++;
          gemHere = false;
          str = "You got a " + gem.getGemType() + " ! :D";
         }//end inventory if
        
        else if(isInventoryFull()){
          background(rock);
          str =  "Your inventory is full! :(";
        }
        
      }//end gemHere if
      
      else if(gemHere == false) {
        background(defaultText);
        str =  "There is no material to mine here";
      }
      
    }//end alive if
    
    else if(alive == false) {
      background(defaultText);
      str =  name + " finds that holding a pickaxe is quite hard when one is incorporeal. Restart to try again.";
    }//end false alive if 
    
    text(str, 190, 681, 1550, 200);
    noLoop();
  }//end Mine
  
 
  public void Deposit() {
      
    if(!isEmpty(inventory)){
      
        for (int count = 0; count < INVENTORY_SLOTS; count++ ) {
          
          if(ba < 1000) {
            
            bankAccount[ba] = inventory[count];
            ba++;
            inventory[count] = null;
            
          }
          
          else
            text("The bank account is full.", 190, 681, 1550, 200);
          
        }
        if(xValue % 10 != 0){
          bankAccount[ba] = null;
          ba--;
        }
        
        i = 0;
    }//end isEmpty if
   
    else if(isEmpty(inventory)) {
     background(defaultText);
     text("The inventory is Empty", 190, 681, 1550, 200);
    }
    
  }//end Deposit
  
  
  public void emptyBank() {
    
    if(!isEmpty(bankAccount)){
    
      background(defaultText);
      text("WARNING: THIS COMMAND WILL DELETE ALL ITEMS IN THE BANK. YOU CANNOT GET THEM BACK. Are you sure you would like to continue? Press 'y' to continue or 'n' to cancel.", 190, 681, 1550, 200); 
      
       if(keyPressed) { 
         
         if(key == 'y') {
            for(int j = 0; j < bankAccount.length; j++) {
              bankAccount[j] = null;
            }
            ba = 0;
            background(defaultText);
            text("Now all that's left is dust.....", 190, 681, 1550, 200);
            noLoop();
         }//end y
         
         else if(key == 'n') {
           background(default1);
         }//end n
        
       }//end keyPressed
       
    }//end isEmpty if
    
    else if(isEmpty(bankAccount)) {
     background(defaultText);
     text("The Bank Account is Empty", 190, 681, 1550, 200); 
    }
    
  }//end emptyBank

  
  public String emptyInventory() {
    
    String end = "";
    
    if(!isEmpty(inventory)) {

        for(int j = 0; j > INVENTORY_SLOTS; j++) {          
          inventory[j] = null;
        }//end for
        
        i = 0;
       end = "It's very clean in here now ^-^";
     
    }//end isEmpty if
    
    else if(isEmpty(inventory)) {
     end = "The inventory is Empty"; 
    }
    
    return end;

  }//end emptyInventory
  
  
  public String accountValue() {
    
    int total = 0;
    
    for(int count = 0; count < bankAccount.length; count++) {
      if(bankAccount[count] != null) {
        total += bankAccount[count].getValue();
      }//end if
    }//end loop
    
    background(defaultText);
    return name + "'s bank account has a value of " + total + ".";
    
  }//end accountValue
  
  public void Bank() {
    
    background(defaultText);
    text(accountValue() + " To empty this account press 't'.", 190, 681, 1550, 200);
    
    if(keyPressed) {
      
      if(key == 't') {
         emptyBank();
      }//end t
      
    }//end keyPressed
    
  }//end Bank
  
  
  public boolean isInventoryFull() {

    boolean full = false;
    
    if(inventory[44] != null) { 
      full = true;  
    }
   
    return full;
    
  }//end isInventoryFull
  
  public boolean isEmpty(Gem[] array) {
    
    if(array[0] == null)
      return true;
    else
      return false;
    
  }//end isEmpty
  
  
  public void inventoryContents() {
    
    int iron = 0, gold = 0, ruby = 0, quartz = 0, diamond = 0, emerald = 0, onyx = 0;
    
    if(!isEmpty(inventory)) {
    
      for(int j = 0; j < inventory.length; j++) {
        
         if(inventory[j] != null) {
            
           if(inventory[j].getGemType() == "Iron")
              iron++;
           else if(inventory[j].getGemType() == "Gold")
              gold++;
           else if(inventory[j].getGemType() == "Ruby")
              ruby++;
           else if(inventory[j].getGemType() == "Quartz")
              quartz++;
           else if(inventory[j].getGemType() == "Diamond")
              diamond++;
           else if(inventory[j].getGemType() == "Emerald")
              emerald++;
           else if(inventory[j].getGemType() == "Onyx")
              onyx++;
              
         }//end != null if
            
      }// end loop
       
      background(defaultText);
      text(name + " has: " + iron + " Iron, " +  gold + " Gold, " +  ruby + " Rubies, " + quartz + " Quartz, " + diamond + " Diamonds, " + emerald + " Emeralds, and " + onyx + " Onyx\nTo empty the inventory press 'e'. To deposit the inventory into the bank press 'd'.", 190, 681, 1550, 200);
      
      if(keyPressed) {
        
        if(key == 'e') {
          background(defaultText);
          emptyInventory();
          text(emptyInventory(), 190, 681, 1550, 200);
        }//end e
        
        else if(key == 'd') {  
          background(defaultText);
          Deposit();
          text("Inventory Deposited", 190, 681, 1550, 200);
        }//end d
        
      }//end keyPressed
      
    }//is Empty if
    
    else if(isEmpty(inventory)) {
      background(defaultText);
      text("The Inventory is Empty", 190, 681, 1550, 200);
      
    }
    
  }//end inventoryContents
  

  public void Rest() {
    background(rest);
    fill(255);
    
    int rand = (int)(Math.random() * 100) + 1;
    String str = "";

    if(alive == true) {
      
      if(rand == 100) {
        str = "It is a tragic day when an adventure has to come to an end and especially so when an adventure ends too soon. This is one of those sad times. " + name + " has died in their sleep. (I am truly sorry that you got this ending because honestly just writing this made me sad and now I need a hug T-T)";
        alive = false;
        noLoop();
      }
      else if(rand >= 1 && rand < 21) {
          str = name + " sat down aginst a tree to read a book. It is a magical tale of heros and dragons and adventure with friends. After hey finished the book " + name + " is ready go get back to adventuring.";
          noLoop();      
      }
      
      else if(rand >= 21 && rand < 41) {
          str = name + " was walking through a field when a sudden rain started to fall. " + name + " took shelter under the welcoming branches of a large oak tree to wait out the weather. As they sit they listen to the sound of water hitting leaves and ground and feel a sense of peace. The rain stops and " + name + " is ready to head out once more.";
          noLoop();
      }
      
      else if(rand >= 41 && rand < 56) {
          str = name + " has traveled far and wide recently and has decided they deserve a nap. They find a lovely little inn and rent a room for a good price. They take a break for a few days before they are ready to once again go out and discover all the secrets of the world.";
          noLoop();     
      }
      
      else if(rand >= 56 && rand < 71) {
          str = name + " returns to the small cottage they have been using as a sort of home base to find a package waiting for them. It is from their parents! Filled with fresh baked goods from their Father and warm clothes bought by their Mother, it certainly a wonderful suprise and must have only come recently. In the letter that comes with the goods their parents wish them luck and remind " + name + " that they are loved. What a wonderful thing to come home to!";
          noLoop();
      }
      
      else if(rand >= 71 && rand < 81) {
          str = name + " decided to take a lunch break near a small, clear stream. As they munch on some carrots and dried fruit, a small bunny comes up to say hello! " + name + " gives the small fluff a little treat and then it hoppes on its way. " + name + " made a little friend!";
          noLoop();
      }
      
      else if(rand >= 81 && rand < 90) {
          str = "As " + name + " is walking through a forrest they hear some odd little barking noises. As they go to investigate, they discover a litter of wolf pups playing a few meters away. Knowing the mother must be close by, " + name + " decides that it would be unwise to get any closer but stays for a few minutes to watch the adorable little balls of fluff. Eventually " + name + " moves on with a full heart and a smile on their face.";
          noLoop();
      }
      
      else if(rand >= 90 && rand < 99) {
          str = name + " decides go visit a nearby town to stock up on supplies and discovers a small shop full of beautiful blankets and tapestries. After much consideration, " + name + "decided to treat themselves to one of these beautiful pieces. They've been working hard, they deserve something nice.";
          noLoop();
      }
      
      else if(rand == 99) {
        
        background(goose);
                      
          if(i != 0){
            
            if(inventory[i-1] == null) {
                str = name + "decides to take a short nap on a comfortable patch of grass near the edge of a forrest. When they wake up they are met with quite the suprise! A goose! A white goose is supposed to be a symbol of good luck so they know to treat this animal with respect. Well, at least until he tries to take one of their gems! Unfortunately for him, " + name + " hasen't found any gems yet. He nips at their ankles for foiling his plans and then waddles off with a honk of dissapointment. ";          
                noLoop();
            }//end null if
                      
            else {
              str = name + " decides to take a short nap on a comfortable patch of grass near the edge of a forrest. When they wake up they are met with quite the suprise! A goose! A white goose is supposed to be a symbol of good luck so you know to treat this animal with respect. Well, at least until he takes one of your gems! The goose runs off with the gem and you are left bewildered in the dust. Damn goose. You lost a " + inventory[i-1].getGemType();
              inventory[i-1] = null;
              i--; 
              noLoop();
            }
         }
         
         else {
             str = name + " decides to take a short nap on a comfortable patch of grass near the edge of a forrest. When they wake up they are met with quite the suprise! A goose! A white goose is supposed to be a symbol of good luck so they know to treat this animal with respect. Well, at least until he tries to take one of their gems! Unfortunately for him, " + name + " hasen't found any gems yet. He nips at their ankles for foiling his plans and then waddles off with a honk of dissapointment. ";          
             noLoop(); 
         }
      }            

    }//end alive if
    
    else if(alive == false) {
      str = "Unfortunately part of being a ghost is being restless. Restart to try again.";
    }
    
    text(str, 190, 681, 1550, 200);

  }//end Rest
  
  public void Info() {
    
    background(defaultText);  
    text("Miner " + name + ": \n\tCurrent X-Value: " + xValue + "     Inventory Is Full: " + isInventoryFull() + "      " + accountValue(), 190, 681, 1550, 200);
    
  }//end toString
    
}//end class


public class Gem {
  
  private String look;
  private int value;
  private String gemType;
  
  public Gem(){
    
    int ran = (int)(Math.random() * 33) + 1;
    
    if(ran >= 1 && ran < 11) {
      isIron();
    }
    else if(ran >= 11 && ran < 19) {
        isGold();
      }
      else if(ran >= 19 && ran < 25) {
          isQuartz();
        }
        else if(ran >= 25 && ran < 29) {
            isRuby();
          }
          else if(ran >= 29 && ran < 31) {
              isDiamond();
            }
            else if(ran >= 31 && ran < 33) {
                isEmerald();
              }
              else if(ran == 33) {
                isOnyx();
                }
                
  }//end constructor
  
  
  public Gem(char g) {

    if(g == 'O') {
      isOnyx();
    }
    else if(g == 'D') {
      isDiamond();
      }
      else if(g == 'E') {
        isEmerald();
        }
        else if(g == 'R') {
          isRuby(); 
          } 
          else if(g == 'I') {
            isIron();
            }
            else if(g == 'G') {
              isGold();
            }

  }//end this strange constructor
  
  
  public void isIron() {
    
    look = "Gray";
    value = 1;
    gemType = "Iron";
    
  }//end isIron
  
  
  public void isGold() {
    
    look = "Gold";
    value = 2;
    gemType = "Gold";
    
  }//end isGold
  
  
  public void isQuartz() {
    
    look = "White";
    value = 3;
    gemType = "Quartz";
    
  }//end isQuartz
  
  
  public void isRuby() {
    
    look = "Red";
    value = 5;
    gemType = "Ruby";
    
  }//end isRuby
  
  
  public void isDiamond() {
    
    look = "Clear";
    value = 8;
    gemType = "Diamond";
    
  }//end isDiamond
  
  
  public void isEmerald() {
    
    look = "Green";
    value = 10;
    gemType = "Emerald";
    
  }//end isEmerald
  
  
  public void isOnyx() {
    
    look = "Black";
    value = 15;
    gemType = "Onyx";
    
  }//end isOnyx
  
  public String getGemType() {
    
    return gemType;
    
  }//end getGemType
  
  public int getValue() {
    
    return value;
    
  }//end getValue
  
  public String getLook() {
    
    return look;
    
  }//end getLook
  
}//end Gem class
  public void settings() {  size(1920, 1020); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Processing_Game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
