###########
loop do 
  clear
  update
  render
end


def update(deltaTime)
  # uniquement changer la machine à états
  runAI(deltaTime)

  renderable.each do
    renderable.update(deltaTime)
  end
end

def runAI
  renderable.each do
    renderable.computeAction(deltaTime)
  end

end
###########

###########
class Renderable
  def update
    move
  end




end
###########

###########
class Tribe
  Action lastAction

  def computeAction(deltaTime)
    if lastAction.olderThan(10.seconds) && lastAction.over?
      @action = Action.new
    end
    @action.update(deltaTime)
  end

  

end
###########

=begin
Actions possibles

Déplacements
Agressions 
Fuites
Neutres



Scénarios possibles

1A 2A -> Baston -> Les deux se battent jusqu'à la mort
1A 2A -> Baston -> Celui qui est en train de perdre essaie de fuir -> 1A 2F

1A 2F -> 1 poursuit 2, pendant un certain temps, sans jamais le rattraper.  -> 1N 2N
1A 2F -> 1 poursuit 2, il le rattrape. 2 est obligé de se battre -> un des deux meurt -> 1N ou 2N


=end
