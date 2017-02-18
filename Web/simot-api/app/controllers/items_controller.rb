class ItemsController < ApplicationController
  before_action :set_items, only: [:show, :update, :destroy]

  def index
    @items = Item.all

    if params[:filter]
      case params[:filter]

      when "name"
        render json: get_by_name
      when "company_name"
        render json: get_by_company_name
      when "urgency"
        render json: get_by_urgency
      end
    else
      render json: @items
    end
  end

  def create
    @item = Deliverable.new(item_params)

    if @item.save
      render json: @item, status: :created, location: @item
    else
      render json: @item.errors, status: :unprocessable_entity
    end
  end

  def show
    render json: @items
  end

  def update
    @item = Deliverable.find(params[:id])

    if @item.update(item_params)
      head :no_content
    else
      render json: @item.errors, status: :unprocessable_entity
    end
  end

  def destroy
    @item.destroy

    head :no_content
  end



  private

    def set_items
      @items = Item.find(params[:id])
    end

    def item_params
      params.require(:item).permit!
    end

    def get_by_name
      Item.order(:item_name)
    end

    def get_by_company_name
      Item.order(:company_name)
    end

    def get_by_urgency
      Item.order(:urgency)
    end

end
