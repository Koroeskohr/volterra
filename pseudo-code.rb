# loop:

loop do 
  clear
  update
  render
end

def update 
  renderable.each do
    renderable.update
  end
end

class Renderable
  def update
    computeAction

  end




end

class Species
  def computeAction
    if lastAction.olderThan(10.seconds) && lastAction.over? do
      @action = Action.new
    end
  end

  

end